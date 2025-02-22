package com.savleen.payment_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PaymentService {
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;
    @Autowired
    private PaymentRepository paymentRepository;

    @KafkaListener(topics = "process_payment_topic")
    public void processPayment(PaymentRequest request) {
        boolean success = Math.random() > 0.2; // Simulating 80% success rate
        if (success) {
            paymentRepository.save(new Payment(UUID.randomUUID().toString(), request.getOrderId(), request.getAmount(), "COMPLETED"));
            kafkaTemplate.send("payment_processed_topic", new PaymentProcessedEvent(request.getOrderId(), true));
        } else {
            kafkaTemplate.send("payment_failed_topic", new PaymentFailedEvent(request.getOrderId()));
        }
    }
}

