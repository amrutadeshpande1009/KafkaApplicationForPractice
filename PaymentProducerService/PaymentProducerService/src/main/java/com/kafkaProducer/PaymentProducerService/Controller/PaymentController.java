package com.kafkaProducer.PaymentProducerService.Controller;

import com.kafkaProducer.PaymentProducerService.Entity.Payment;
import com.kafkaProducer.PaymentProducerService.Service.PaymentService;
import com.kafkaProducer.PaymentProducerService.model.OrderCreatedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping
    public Payment create(@RequestBody Payment payment) {
        return paymentService.createPayment(payment);
    }

    @GetMapping
    public List<Payment> all() {
        return paymentService.getAllPayments();
    }
}

