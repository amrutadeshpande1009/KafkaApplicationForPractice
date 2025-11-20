package com.kafkaProducer.PaymentProducerService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication

public class PaymentProducerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaymentProducerServiceApplication.class, args);
	}

}
