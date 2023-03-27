package com.example.belajar_spring.service;

import com.example.belajar_spring.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    List<User> findAll();
    Optional<User> findById(Long id);
    void updateById(User user);
    User create(User user);
}
