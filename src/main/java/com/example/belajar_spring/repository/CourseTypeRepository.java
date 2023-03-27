package com.example.belajar_spring.repository;

import com.example.belajar_spring.model.CourseType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseTypeRepository extends JpaRepository<CourseType, Long> {
    boolean existsByTypeIgnoreCase(String type);
}