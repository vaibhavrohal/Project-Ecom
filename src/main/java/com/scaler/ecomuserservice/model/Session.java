package com.scaler.ecomuserservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
public class Session extends BaseModel{
    private String token;
    @ManyToOne
    private User user;
    private Date loginAt;
    private Date expiringAt;
    @Enumerated(EnumType.STRING)
    private SessionStatus sessionStatus;
}
