package com.savleen.order_service.orderEvent;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderCreatedEvent {
    private Long orderId;
    private String productId;
    private String userId;
}

