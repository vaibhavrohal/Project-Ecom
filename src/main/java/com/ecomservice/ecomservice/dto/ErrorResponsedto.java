package com.ecomservice.ecomservice.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ErrorResponsedto {
    private String message;
    private int errorcode;

    public ErrorResponsedto() {
    }
}
