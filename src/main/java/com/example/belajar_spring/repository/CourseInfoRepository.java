package com.example.belajar_spring.repository;

import com.example.belajar_spring.model.CourseInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseInfoRepository extends JpaRepository<CourseInfo, Long> {
}