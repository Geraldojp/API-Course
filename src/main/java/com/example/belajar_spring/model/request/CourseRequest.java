package com.example.belajar_spring.model.request;

import com.example.belajar_spring.model.CourseInfo;
import com.example.belajar_spring.model.CourseType;
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
    private String duration;
    private String level;
    private CourseType courseType;

}
