package com.example.belajar_spring.model.request;

import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link com.example.belajar_spring.model.CourseType} entity
 */
@Data
public class CourseTypeDto implements Serializable {
    private String type;
}