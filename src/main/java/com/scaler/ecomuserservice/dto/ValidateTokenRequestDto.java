package com.scaler.ecomuserservice.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ValidateTokenRequestDto {
    private Long userId;
    private String token;
}
