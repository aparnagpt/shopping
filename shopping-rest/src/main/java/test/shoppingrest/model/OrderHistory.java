package test.shoppingrest.model;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class OrderHistory {
    private List<Product> products;
    private String customerName;
    private String address;
    private int orderId;
    private Date date;

    public OrderHistory() {
    }

    public OrderHistory(List<Product> products, String customerName, String address, int orderId, Date date) {
        this.products = products;
        this.customerName = customerName;
        this.address = address;
        this.orderId = orderId;
        this.date = date;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getAddress() {
        return address;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Date getDate() {
        return date;
    }

    public List<Product> getProducts() {
        return products;
    }
}