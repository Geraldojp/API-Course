package com.example.belajar_spring.utils;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UUIDGenerator implements IRandomStringGenerator {
    @Override
    public String random() {
        return UUID.randomUUID().toString();
    }
}
