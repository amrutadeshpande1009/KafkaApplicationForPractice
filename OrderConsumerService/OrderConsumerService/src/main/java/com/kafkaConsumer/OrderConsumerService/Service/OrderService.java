package com.kafkaConsumer.OrderConsumerService.Service;

import com.kafkaConsumer.OrderConsumerService.Entity.Order;
import com.kafkaConsumer.OrderConsumerService.Repository.OrderRepository;
import com.kafkaConsumer.OrderConsumerService.model.OrderCreatedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate;
    private final OrderRepository repo;

    @Autowired
    public OrderService(KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate, OrderRepository repo) {
        this.kafkaTemplate = kafkaTemplate;
        this.repo = repo;
    }

    public Order createOrder(Order order) {
        order.setStatus("CREATED");
        Order saved = repo.save(order);
        OrderCreatedEvent event = new OrderCreatedEvent(saved.getId(), saved.getProductName(), saved.getQuantity(), saved.getStatus());
        kafkaTemplate.send("order-topic", event);
        return saved;
    }
}