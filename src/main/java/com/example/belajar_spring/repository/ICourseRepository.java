package com.example.belajar_spring.repository;

import com.example.belajar_spring.model.Course;
import com.example.belajar_spring.utils.CourseKey;

import java.util.List;
import java.util.Optional;

public interface ICourseRepository {
    List<Course> getAll() throws Exception;
    Course create(Course course)throws Exception;
    Optional<Course> findById(String id)throws Exception;
    void update(Course course,String id)throws Exception;
    void delete(String id)throws Exception;
    Optional<List<Course>> findBy(CourseKey with, String value)throws Exception;
}
