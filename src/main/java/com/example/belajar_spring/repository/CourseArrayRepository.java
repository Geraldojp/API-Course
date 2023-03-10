package com.example.belajar_spring.repository;

import com.example.belajar_spring.model.Course;

import java.util.List;
import java.util.Optional;

public class CourseRepository implements ICourseRepository {
    @Override
    public List<Course> getAll() throws Exception {
        return null;
    }

    @Override
    public Course create(Course course) throws Exception {
        return null;
    }

    @Override
    public Optional<Course> findById(String id) throws Exception {
        return Optional.empty();
    }

    @Override
    public void update(Course course, String id) throws Exception {

    }

    @Override
    public void delete(String id) {

    }
}
