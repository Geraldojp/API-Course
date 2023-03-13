package com.example.belajar_spring.repository;

import com.example.belajar_spring.model.Course;
import com.example.belajar_spring.utils.CourseKey;
import com.example.belajar_spring.utils.IRandomStringGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Repository
public class CourseArrayRepository implements ICourseRepository {
    private List<Course> courses = new ArrayList<>();
    @Autowired
    private IRandomStringGenerator randomStringGenerator;
    @Override
    public List<Course> getAll() throws Exception {
        return courses;
    }

    @Override
    public Course create(Course course) throws Exception {
        course.setCourseId(randomStringGenerator.random());
        courses.add(course);
        return course;
    }

    @Override
    public Optional<Course> findById(String id) throws Exception {
        for(Course course: courses){
            if (course.getCourseId().equals(id)){
                return Optional.of(course);
            }
        }
        return Optional.empty();
    }

    @Override
    public void update(Course course, String id) throws Exception {
        for (Course existingCourse: courses){
            if (existingCourse.getCourseId().equals(id)){
                existingCourse.setTitle(course.getTitle());
                existingCourse.setDescription(course.getDescription());
                existingCourse.setLink(course.getLink());
                break;
            }
        }
    }

    @Override
    public void delete(String id) {
        for (Course course: courses){
            if(course.getCourseId().equals(id)){
                courses.remove(course);
                break;
            }
        }
    }

    @Override
    public Optional<List<Course>> findBy(CourseKey with, String value) {
        List<Course> courseList = new ArrayList<>();
        switch (with){
            case title -> {
                for (Course course: courses){
                    if(course.getTitle().toLowerCase().equals(value.toLowerCase())){
                        courseList.add(course);
                    }
                }
            }
            case description ->{
                for (Course course: courses){
                    if(course.getDescription().toLowerCase().equals(value.toLowerCase())){
                        courseList.add(course);
                    }
                }
            }
            case link ->{
                for (Course course: courses){
                    if(course.getLink().toLowerCase().equals(value.toLowerCase())){
                        courseList.add(course);
                    }
                }
            }
        }
        return courseList.isEmpty() ? Optional.empty() : Optional.of(courseList);
    }
}
