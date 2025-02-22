package com.savleen.inventory_service.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InventoryReserveRequest {
    private String orderId;
    private String productId;
}

