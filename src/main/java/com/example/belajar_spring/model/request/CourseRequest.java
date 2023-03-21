package com.example.belajar_spring.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CourseRequest {
    @NotBlank(message = "{title.required}")
    private String title;
    private String description;
    @NotBlank(message = "{link.required}")
    private String link;
}
