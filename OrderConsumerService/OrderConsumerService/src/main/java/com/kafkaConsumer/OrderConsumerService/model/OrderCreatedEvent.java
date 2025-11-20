package com.kafkaConsumer.OrderConsumerService.model;


public class OrderCreatedEvent {

    private Long orderId;
    private String productName;
    private int quantity;
    private String status;
    public OrderCreatedEvent() {}
    public OrderCreatedEvent(Long orderId, String productName, int quantity, String status) {
        this.orderId = orderId; this.productName = productName; this.quantity = quantity; this.status = status;
    }


    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "OrderCreatedEvent{" +
                "orderId=" + orderId +
                ", productName='" + productName + '\'' +
                ", quantity=" + quantity +
                ", status='" + status + '\'' +
                '}';
    }
}