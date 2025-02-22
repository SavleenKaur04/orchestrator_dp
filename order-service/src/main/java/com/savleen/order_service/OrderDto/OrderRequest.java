package com.savleen.order_service.OrderDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequest {
    private String productId;
    private String userId;
}

