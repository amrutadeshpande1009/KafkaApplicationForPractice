package com.kafkaConsumer.OrderConsumerService.Repository;

import com.kafkaConsumer.OrderConsumerService.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
