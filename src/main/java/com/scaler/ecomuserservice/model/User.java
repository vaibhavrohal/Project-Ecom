package com.scaler.ecomuserservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity(name="EcomServiceUser")
public class User extends BaseModel{
    private String profileName;
    private String email;
    private String password;
    @ManyToMany
    private Set<Role> rolls;
}
