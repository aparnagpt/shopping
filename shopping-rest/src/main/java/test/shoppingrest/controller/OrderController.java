package test.shoppingrest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import test.shoppingrest.model.Order;
import test.shoppingrest.model.OrderHistory;
import test.shoppingrest.service.DbOrderService;

import java.util.Date;
import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private DbOrderService orderService;

    @CrossOrigin(origins = "*")
    @PostMapping("/order")
    public Integer createOrder(@RequestBody Order order) {
        Order createdOrder = orderService.createOrder(order);
        return createdOrder.getOrderId();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/orders")
    public List<OrderHistory> getOrders() {
        return orderService.getOrders(new Date());
    }
}
