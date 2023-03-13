package com.example.belajar_spring.model;

import lombok.Getter;
import lombok.Setter;

public class Course {
    @Getter@Setter
    private String courseId;
    @Getter@Setter
    private  String title;
    @Getter@Setter
    private String description;
    @Getter@Setter
    private String link;

    public Course(String courseId, String title, String description, String link) {
        this.courseId = courseId;
        this.title = title;
        this.description = description;
        this.link = link;
    }

    public Course() {
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseId='" + courseId + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", link='" + link + '\'' +
                '}';
    }
}
