package com.kafkaProducer.PaymentProducerService.Service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafkaProducer.PaymentProducerService.Entity.Payment;
import com.kafkaProducer.PaymentProducerService.Repository.PaymentRepository;
import com.kafkaProducer.PaymentProducerService.model.OrderCreatedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;



    @KafkaListener(topics = "order-topic", groupId = "payment-group")
    public void consumeOrderEvent(String message) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        OrderCreatedEvent event = mapper.readValue(message, OrderCreatedEvent.class);
        System.out.println("Payment Service received: " + event);
    }



    public Payment createPayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }




}

