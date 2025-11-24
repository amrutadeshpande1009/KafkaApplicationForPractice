package com.kafkaConsumer.OrderConsumerService.ControllerTest;

import com.kafkaConsumer.OrderConsumerService.Controller.OrderController;
import com.kafkaConsumer.OrderConsumerService.Entity.Order;
import com.kafkaConsumer.OrderConsumerService.Service.OrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderControllerTest {

    @Mock
    private OrderService orderService;

    @InjectMocks
    private OrderController orderController;

    @Test
    void testCreateOrder() {
        // Arrange
        Order order = new Order();
        order.setProductName("Laptop");
        order.setQuantity(1);

        Order savedOrder = new Order();
        savedOrder.setId(1L);
        savedOrder.setProductName("Laptop");
        savedOrder.setQuantity(1);
        savedOrder.setStatus("CREATED");

        when(orderService.createOrder(order)).thenReturn(savedOrder);

        // Act
        Order result = orderController.create(order);


        assertEquals("Laptop", result.getProductName());
        assertEquals("CREATED", result.getStatus());
        verify(orderService, times(1)).createOrder(order);
    }
}