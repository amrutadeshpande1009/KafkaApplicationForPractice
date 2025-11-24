package com.kafkaProducer.PaymentProducerService.ServiceTest;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafkaProducer.PaymentProducerService.Entity.Payment;
import com.kafkaProducer.PaymentProducerService.Repository.PaymentRepository;
import com.kafkaProducer.PaymentProducerService.Service.PaymentService;
import com.kafkaProducer.PaymentProducerService.model.OrderCreatedEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PaymentServiceTest {

    @Mock
    private PaymentRepository paymentRepository;

    @InjectMocks
    private PaymentService paymentService;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    void testConsumeOrderEvent() throws Exception {
        String jsonMessage = "{\"orderId\":1,\"productName\":\"Laptop\",\"quantity\":1,\"status\":\"CREATED\"}";

        // Act
        paymentService.consumeOrderEvent(jsonMessage);

        // Assert
        OrderCreatedEvent event = objectMapper.readValue(jsonMessage, OrderCreatedEvent.class);
        assertEquals(1, event.getOrderId());
        assertEquals("Laptop", event.getProductName());
    }

    @Test
    void testCreatePayment() {
        Payment payment = new Payment();
        payment.setOrderId(1L);
        payment.setAmount(1000.0);
        payment.setStatus("COMPLETED");

        when(paymentRepository.save(payment)).thenReturn(payment);

        Payment savedPayment = paymentService.createPayment(payment);

        assertNotNull(savedPayment);
        assertEquals(1, savedPayment.getOrderId());
        verify(paymentRepository, times(1)).save(payment);
    }

    @Test
    void testGetAllPayments() {
        Payment p1 = new Payment();
        p1.setOrderId(1L);
        Payment p2 = new Payment();
        p2.setOrderId(2L);

        when(paymentRepository.findAll()).thenReturn(Arrays.asList(p1, p2));

        List<Payment> payments = paymentService.getAllPayments();

        assertEquals(2, payments.size());
        verify(paymentRepository, times(1)).findAll();
    }
}