package com.example.belajar_spring.repository;

import com.example.belajar_spring.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}