package com.kafkaConsumer.OrderConsumerService.Entity;
import jakarta.persistence.*;


@Entity
@Table(name = "\"order\"")
public class Order {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    private String productName;
    private int quantity;
    private String status;


    public Long getId() {
        return orderId;
    }

    public void setId(Long Id) {
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


}


