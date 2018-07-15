package test.shoppingrest.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import test.shoppingrest.model.Order;
import test.shoppingrest.model.OrderHistory;
import test.shoppingrest.model.Product;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class StaticOrderService {
    private static final List<Order> ORDERS = new ArrayList<>();
    @Autowired
    private StaticProductService productService;

    public Order createOrder(Order order) {
        order.setOrderId(ORDERS.size() + 1);
        ORDERS.add(order);
        return order;
    }

    public List<OrderHistory> getOrders(Date date) {
        return ORDERS.stream()
                .filter(order -> order.getDate().getDate() == date.getDate())
                .map(order -> new OrderHistory(getProducts(order.getProductId()), order.getCustomerName(), order.getAddress(), order.getOrderId(), order.getDate()))
                .collect(Collectors.toList());
    }

    private List<Product> getProducts(List<Integer> productIds) {
       return productIds.stream().map(productService::getProductDetail).collect(Collectors.toList());
    }
 }
