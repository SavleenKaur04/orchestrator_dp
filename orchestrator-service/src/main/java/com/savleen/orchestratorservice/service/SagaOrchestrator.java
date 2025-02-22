package com.savleen.orchestratorservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SagaOrchestrator {
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @KafkaListener(topics = "order_created_topic")
    public void handleOrderCreated(OrderCreatedEvent event) {
        kafkaTemplate.send("reserve_inventory_topic", new InventoryReserveRequest(event.getOrderId(), event.getProductId()));
    }

    @KafkaListener(topics = "inventory_reserved_topic")
    public void handleInventoryReserved(InventoryReservedEvent event) {
        kafkaTemplate.send("process_payment_topic", new PaymentRequest(event.getOrderId(), 100));
    }

    @KafkaListener(topics = "payment_processed_topic")
    public void handlePaymentProcessed(PaymentProcessedEvent event) {
        kafkaTemplate.send("ship_order_topic", new ShippingRequest(event.getOrderId()));
    }

    @KafkaListener(topics = "payment_failed_topic")
    public void handlePaymentFailed(PaymentFailedEvent event) {
        kafkaTemplate.send("cancel_inventory_reservation_topic", new CancelInventoryRequest(event.getOrderId()));
    }
}

