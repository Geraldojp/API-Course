package com.example.belajar_spring.service;

import com.example.belajar_spring.model.Course;
import com.example.belajar_spring.model.request.CourseRequest;
import com.example.belajar_spring.utils.CourseKey;

import java.util.List;
import java.util.Optional;

public interface ICourseService {
     List<Course> list();
     Course create(Course course);
     Optional<Course> get(String id);
     void update(Course course, String id);
     void delete(String id);
     Optional<List<Course>> findBy(CourseKey with, String value);

}
