package com.ecomservice.ecomservice.dto;

import com.ecomservice.ecomservice.model.Category;
import com.ecomservice.ecomservice.model.Price;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ProductResponsedto {
    private UUID id;
    private String title;
    private double price;
    private String category;
    private String description;
    private String image;
}
