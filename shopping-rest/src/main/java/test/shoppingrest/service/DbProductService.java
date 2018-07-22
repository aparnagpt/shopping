package test.shoppingrest.service;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import test.shoppingrest.model.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@Component
public class DbProductService {

    public List<Product> getAllProducts() {
        return DbUtil.mySqlJdbcTemplate().query("select id, name, amount from Product", this::mapToMinimalProduct);
    }

    public Product getProductDetail(int id) {
        return DbUtil.mySqlJdbcTemplate().queryForObject("select * from Product where id = ?", this::mapToProduct, id);
    }

    public List<Product> getProducts(List<String> ids) {
        return new NamedParameterJdbcTemplate(DbUtil.mySqlJdbcTemplate()).query("select * from Product where id in (:ids)", Collections.singletonMap("ids", ids), this::mapToProduct);
    }

    private Product mapToMinimalProduct(ResultSet rs, int i) throws SQLException {
        return new Product(rs.getInt("id"), rs.getString("name"), rs.getInt("amount"));
    }

    private Product mapToProduct(ResultSet rs, int i) throws SQLException {
        return new Product(rs.getInt("id"), rs.getString("name"), rs.getInt("amount"), rs.getString("description"));
    }
}