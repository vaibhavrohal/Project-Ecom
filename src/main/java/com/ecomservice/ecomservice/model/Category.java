package com.ecomservice.ecomservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Entity
public class Category extends BaseModel{
    private String categoryName;

}
