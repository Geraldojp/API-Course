package com.example.belajar_spring.service;

import com.example.belajar_spring.model.CourseInfo;
import com.example.belajar_spring.repository.CourseInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CourseInfoService implements ICourseService<CourseInfo> {
    @Autowired
    private CourseInfoRepository courseInfoRepository;
    public Iterable<CourseInfo> findAll(Pageable pageable){
        return courseInfoRepository.findAll(pageable);
    }
    public CourseInfo create(CourseInfo courseInfo){
        return courseInfoRepository.save(courseInfo);
    }
    public CourseInfo update(CourseInfo courseInfo, Long id){
        return courseInfoRepository.save(courseInfo);
    }
    public void deleteById(Long id){
        courseInfoRepository.deleteById(id);
    }
    public Optional<CourseInfo> findById(Long id){
        return courseInfoRepository.findById(id);
    }
}
