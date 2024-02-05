package com.ecomservice.ecomservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Setter
@Getter
@MappedSuperclass
public abstract class BaseModel {
    @Id
    @GeneratedValue (generator="uuidGenerator")
    @GenericGenerator(name="uuidGenerator",strategy="uuid2") // incase of my sql binary too need to be added. postgres take it automatically
    @Column(name="id",nullable=false,updatable=false)
    private UUID id;

}
