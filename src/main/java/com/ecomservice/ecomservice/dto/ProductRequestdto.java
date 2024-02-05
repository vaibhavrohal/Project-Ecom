package com.ecomservice.ecomservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequestdto {

    private String title;
    private  double price;
    private String category;
    private String description;
    private String image;
}
