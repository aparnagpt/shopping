package test.shoppingrest.model;

import java.util.Date;
import java.util.List;

public class Order {
    private int orderId;
    private List<Integer> productId;
    private String customerName;
    private String address;
    private Date date;

    public Order() {
    }

    public List<Integer> getProductId() {
        return productId;
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


    public void setProductId(List<Integer> productId) {
        this.productId = productId;
    }

}
