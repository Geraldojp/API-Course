package com.example.belajar_spring.service;

import com.example.belajar_spring.model.Course;
import com.example.belajar_spring.repository.ICourseRepository;
import com.example.belajar_spring.utils.CourseKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CourseService implements ICourseService {
    @Autowired
    private ICourseRepository courseRepository;
    @Override
    public List<Course> list() {
        try {
            List<Course> courses = courseRepository.getAll();
            if(courses.isEmpty()){
                throw new Exception("Empty");
            }
            return courses;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Course create(Course course) {
        try {
            Optional<List<Course>> findCourse = courseRepository.findBy(CourseKey.title, course.getTitle());
            if(findCourse.isPresent()){
                throw new IllegalStateException("Already Exist");
            }else {
                return courseRepository.create(course);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Course> get(String id) {
        try {
            Optional<Course> find = courseRepository.findById(id);
            if(find.isEmpty()){
                throw new Exception("No such id");
            }
            return find;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Course course, String id) {
        try {
            Optional<Course> find = courseRepository.findById(id);
            if(find.isEmpty()){
                throw new Exception("ID not found");
            }
            courseRepository.update(course,id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(String id) {
        try {
            Optional<Course> find = courseRepository.findById(id);
            if(find.isEmpty()){
                throw new Exception("ID not found");
            }
            courseRepository.delete(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<List<Course>> findBy(CourseKey with, String value) {
        try {
            Optional<List<Course>> find = courseRepository.findBy(with,value);
            if(find.isEmpty()){
                throw new Exception("Not found");
            }
            return find;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
