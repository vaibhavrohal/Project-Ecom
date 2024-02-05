package com.ecomservice.ecomservice.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
public class Product extends BaseModel {

    private String title;
    @OneToOne
    private Price price;
    @ManyToOne

    private Category category;
    private String description;
    private String image;
   @ManyToMany //(mappedBy = "productsList") // now only one mapping table is created ,though relationship is bidirectional i.e product id with order id
   private List<Order> orders;
}
