package com.savleen.inventory_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;
    @Autowired
    private InventoryRepository inventoryRepository;

    @KafkaListener(topics = "reserve_inventory_topic")
    public void reserveInventory(InventoryReserveRequest request) {
        Inventory inventory = inventoryRepository.findById(request.getProductId()).orElse(null);
        if (inventory != null && inventory.getStock() > 0) {
            inventory.setStock(inventory.getStock() - 1);
            inventoryRepository.save(inventory);
            kafkaTemplate.send("inventory_reserved_topic", new InventoryReservedEvent(request.getOrderId(), request.getProductId()));
        }
    }
}
