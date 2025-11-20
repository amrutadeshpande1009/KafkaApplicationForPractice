package com.kafkaConsumer.OrderConsumerService.Controller;


import com.kafkaConsumer.OrderConsumerService.Entity.Order;
import com.kafkaConsumer.OrderConsumerService.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @PostMapping
    public Order create(@RequestBody Order order)
    {
        return orderService.createOrder(order);
    }


}



