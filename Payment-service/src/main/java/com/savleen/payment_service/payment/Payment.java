package com.savleen.payment_service.payment;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Payment {
    @Id
    private String paymentId;
    private String orderId;
    private double amount;
    private String status;
}

