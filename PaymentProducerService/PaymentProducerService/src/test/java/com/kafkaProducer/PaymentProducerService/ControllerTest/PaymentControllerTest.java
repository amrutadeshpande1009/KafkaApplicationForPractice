package com.kafkaProducer.PaymentProducerService.ControllerTest;

import com.kafkaProducer.PaymentProducerService.Controller.PaymentController;
import com.kafkaProducer.PaymentProducerService.Entity.Payment;
import com.kafkaProducer.PaymentProducerService.Service.PaymentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PaymentControllerTest {

    @Mock
    private PaymentService paymentService;

    @InjectMocks
    private PaymentController paymentController;

    @Test
    void testCreatePayment() {
        Payment payment = new Payment();
        payment.setOrderId(1L);
        payment.setAmount(1000.0);
        payment.setStatus("COMPLETED");

        when(paymentService.createPayment(payment)).thenReturn(payment);

        Payment result = paymentController.create(payment);

        assertEquals(1, result.getOrderId());
        assertEquals("COMPLETED", result.getStatus());
        verify(paymentService, times(1)).createPayment(payment);
    }

    @Test
    void testGetAllPayments() {
        Payment p1 = new Payment();
        p1.setOrderId(1L);
        Payment p2 = new Payment();
        p2.setOrderId(2L);

        when(paymentService.getAllPayments()).thenReturn(Arrays.asList(p1, p2));

        List<Payment> result = paymentController.all();

        assertEquals(2, result.size());
        verify(paymentService, times(1)).getAllPayments();
    }
}
