package com.kafkaProducer.PaymentProducerService.dto;


public class OrderEvent {
    private String orderId;
    private String productName;
    private double amount;

    public OrderEvent() {
    }

    public OrderEvent(String orderId, String productName, double amount) {
        this.orderId = orderId;
        this.productName = productName;
        this.amount = amount;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }



}

