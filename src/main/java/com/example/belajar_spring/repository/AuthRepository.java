package com.example.belajar_spring.repository;

import com.example.belajar_spring.model.Auth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepository extends JpaRepository<Auth, String> {
    boolean existsByEmailIgnoreCase(String email);
    Auth findByEmailIgnoreCase(String email);
}