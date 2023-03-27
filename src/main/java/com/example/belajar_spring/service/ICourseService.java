package com.example.belajar_spring.service;

import com.example.belajar_spring.model.Course;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ICourseService <T> {
    public Iterable<T> findAll(Pageable pageable);
    public T create(T t);
    public T update(T t, Long id);
    public void deleteById(Long id);
    public Optional<T> findById(Long id);

}
