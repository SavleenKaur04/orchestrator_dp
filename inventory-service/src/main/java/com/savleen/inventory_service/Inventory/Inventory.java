package com.savleen.inventory_service.Inventory;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Inventory {
    @Id
    private String productId;
    private int stock;
}

