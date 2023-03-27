package com.example.belajar_spring.service;


import com.example.belajar_spring.exception.ExistedDataException;
import com.example.belajar_spring.exception.NotFoundException;
import com.example.belajar_spring.model.CourseType;
import com.example.belajar_spring.model.request.CourseTypeDto;
import com.example.belajar_spring.repository.CourseTypeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CourseTypeService implements ICourseService<CourseType> {
    @Autowired
    private CourseTypeRepository courseTypeRepository;
    @Autowired
    private ModelMapper modelMapper;
    public Iterable<CourseType> findAll(Pageable pageable){
        return courseTypeRepository.findAll(pageable);
    }

    @Override
    public CourseType create(CourseType courseType) {
        return null;
    }

    public CourseType create(CourseTypeDto courseType){
        try {
            if (courseTypeRepository.existsByTypeIgnoreCase(courseType.getType())) {
                throw new ExistedDataException("Course Type already exist");
            }
            CourseType newCourse = modelMapper.map(courseType, CourseType.class);
            return courseTypeRepository.save(newCourse);
        }catch (ExistedDataException e){
            throw e;
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    public CourseType update(CourseType courseType, Long id){
        try {
            Optional<CourseType> find = courseTypeRepository.findById(id);
            if (find.isEmpty()) {
                throw new NotFoundException("Data not found");
            }
            return courseTypeRepository.save(courseType);
        }catch (NotFoundException e){
            throw e;
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    public void deleteById(Long id){
        try{
            Optional<CourseType> find = courseTypeRepository.findById(id);
            if (find.isEmpty()) {
                throw new NotFoundException("Data not found");
            }
            courseTypeRepository.deleteById(id);
        }catch (NotFoundException e){
            throw e;
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    public Optional<CourseType> findById(Long id){
        try {
            Optional<CourseType> find = courseTypeRepository.findById(id);
            if (find.isEmpty()) {
                throw new NotFoundException("Data not found");
            }
            return find;
        }catch (NotFoundException e){
            throw e;
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
