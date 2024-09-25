package lk.ijse.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.math.BigDecimal;

@Entity

public class Orders {

    @Id
    private String orderId;
    private String orderDate;
    private String cusId;
    private String cusName;
    private BigDecimal totalPrice;

    public Orders() {
    }

    public Orders(String orderId, String orderDate, String cusId, String cusName, BigDecimal totalPrice) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.cusId = cusId;
        this.cusName = cusName;
        this.totalPrice = totalPrice;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getCusId() {
        return cusId;
    }

    public void setCusId(String cusId) {
        this.cusId = cusId;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", orderDate='" + orderDate + '\'' +
                ", cusId='" + cusId + '\'' +
                ", cusName='" + cusName + '\'' +
                ", totalPrice=" + totalPrice +
                '}';
    }
}