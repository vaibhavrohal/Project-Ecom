package com.ecomservice.ecomservice.model;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class Price extends BaseModel{
    private double amount;
    private double discount;
    private String currency;
}
