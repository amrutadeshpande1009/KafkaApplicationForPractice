package com.kafkaConsumer.OrderConsumerService.ServiceTest;

import com.kafkaConsumer.OrderConsumerService.Entity.Order;
import com.kafkaConsumer.OrderConsumerService.Repository.OrderRepository;
import com.kafkaConsumer.OrderConsumerService.Service.OrderService;
import com.kafkaConsumer.OrderConsumerService.model.OrderCreatedEvent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate;

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService orderService;

    @Test
    void testCreateOrder() {
        // Arrange
        Order order = new Order();
        order.setProductName("Laptop");
        order.setQuantity(1);

        Order savedOrder = new Order();
        savedOrder.setId(1L); // Long type
        savedOrder.setProductName("Laptop");
        savedOrder.setQuantity(1);
        savedOrder.setStatus("CREATED");

        when(orderRepository.save(any(Order.class))).thenReturn(savedOrder);

        // Act
        Order result = orderService.createOrder(order);


        assertEquals("CREATED", result.getStatus());


        verify(orderRepository, times(1)).save(any(Order.class));


        ArgumentCaptor<OrderCreatedEvent> eventCaptor = ArgumentCaptor.forClass(OrderCreatedEvent.class);
        verify(kafkaTemplate, times(1)).send(eq("order-topic"), eventCaptor.capture());

        OrderCreatedEvent event = eventCaptor.getValue();

        assertEquals("Laptop", event.getProductName());
        assertEquals(1, event.getQuantity());
        assertEquals("CREATED", event.getStatus());
    }
}