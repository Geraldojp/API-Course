package com.example.belajar_spring.service;

import com.example.belajar_spring.model.request.LoginRequest;
import com.example.belajar_spring.model.request.RegisterRequest;

public interface IAuthService {
    String register(RegisterRequest registerRequest);

//    Ga jadi digunakan karena ada class yang implement interface ini
//    @Query("SELECT u FROM User u WHERE u.auth.email = ?1 and u.auth.password = ?2")
//    String login(String email, String password);

    String login(LoginRequest loginRequest);
}
