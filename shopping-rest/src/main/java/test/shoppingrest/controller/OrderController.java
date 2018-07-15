package test.shoppingrest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import test.shoppingrest.model.Order;
import test.shoppingrest.model.OrderHistory;
import test.shoppingrest.service.DbOrderService;

import java.net.URI;
import java.util.Date;
import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private DbOrderService orderService;

    @PostMapping("/order")
    public ResponseEntity<Void> createOrder(@RequestBody Order order) {
       Order createdOrder = orderService.createOrder(order);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path(
                "/{id}").buildAndExpand(createdOrder.getOrderId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/orders")
    public List<OrderHistory> getOrders() {
        return orderService.getOrders(new Date());
    }
}
