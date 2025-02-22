package com.savleen.payment_service.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentRequest {
    private String orderId;
    private double amount;
}
