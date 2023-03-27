package com.example.belajar_spring.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Table(name = "auth")
public class Auth {
    @Id
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "is_active", columnDefinition = "boolean default false")
    private boolean isActive;

}