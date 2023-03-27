package com.example.belajar_spring.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class RegisterRequest {
    private String name;
    private String phone;
    private String email;
    private String password;
}
