package test.shoppingrest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import test.shoppingrest.model.Order;
import test.shoppingrest.model.OrderHistory;
import test.shoppingrest.model.Product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Component
public class DbOrderService {

    @Autowired
    private DbProductService dbProductService;

    public Order createOrder(Order order) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        DbUtil.mySqlJdbcTemplate().update(connection -> {
            PreparedStatement ps = connection.prepareStatement("insert into shopping_order (customerName, customerAddress, date) values (?, ?, ?)", new String[]{"id"});
            ps.setString(1, order.getCustomerName());
            ps.setString(2, order.getAddress());
            ps.setString(3, new SimpleDateFormat("yyyy-MM-dd").format(order.getDate()));
            return ps;
        }, keyHolder);
        order.setOrderId(keyHolder.getKey().intValue());
        order.getProductId().forEach(productId -> {
            DbUtil.mySqlJdbcTemplate().update("insert into shopping_order_row (order_id, product_id) values (?, ?)", order.getOrderId(), productId);
        });
        return order;
    }

    public List<OrderHistory> getOrders(Date date) {
        return DbUtil.mySqlJdbcTemplate().query("select\n" +
                "  so.id,\n" +
                "  GROUP_CONCAT(sor.product_id) as pids,\n" +
                "  so.customername,\n" +
                "  so.customeraddress,\n" +
                "  so.date\n" +
                "from shopping_order so\n" +
                "  join shopping_order_row sor on so.id = sor.order_id\n" +
                "where so.date = ?\n" +
                "group by so.id, so.customername, so.customeraddress, so.date", this::mapToOrderHistory, new SimpleDateFormat("yyyy-MM-dd").format(date));
    }

    private OrderHistory mapToOrderHistory(ResultSet rs, int i) throws SQLException {
        List<Product> products = dbProductService.getProducts(Arrays.asList(rs.getString("pids").split(",")));
        try {
            return new OrderHistory(products, rs.getString("customername"), rs.getString("customeraddress"), rs.getInt("id"), new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("date")));
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
