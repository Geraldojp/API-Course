package com.example.belajar_spring.model.response;

import lombok.Getter;
import lombok.Setter;

public abstract class CommonResponse {
    @Getter@Setter
    private String status;
    @Getter@Setter
    private String message;
    @Getter@Setter
    private String code;
}
