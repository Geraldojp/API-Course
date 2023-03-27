package com.example.belajar_spring.controller;

import com.example.belajar_spring.model.CourseType;
import com.example.belajar_spring.model.request.CourseTypeDto;
import com.example.belajar_spring.model.response.SuccessResponse;
import com.example.belajar_spring.service.CourseTypeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/course-type")
public class CourseTypeController {
    @Autowired
    private CourseTypeService courseTypeService;
    @Autowired
    private ModelMapper modelMapper;
    @GetMapping
    public ResponseEntity findAll(int page, String sort){
        Pageable pageable = PageRequest.of(page, 10, Sort.by("courseTypeId").ascending());
        if(sort.equalsIgnoreCase("desc")){
            pageable = PageRequest.of(page, 10, Sort.by("courseTypeId").descending());
        }
         Iterable<CourseType>result = courseTypeService.findAll(pageable);
        return ResponseEntity.status(HttpStatus.OK).
                body(new SuccessResponse<>("Success", result));
    }
    @PostMapping
    public ResponseEntity create(@RequestBody CourseTypeDto courseType){
        CourseType courseType1 = modelMapper.map(courseType, CourseType.class);
        CourseType newCourse = courseTypeService.create(courseType1);
        return ResponseEntity.status(HttpStatus.CREATED).
                body(new SuccessResponse<>("Created", newCourse));
    }
    @PutMapping("/{id}")
    public ResponseEntity update(@RequestBody CourseType courseType, @PathVariable Long id){
        courseTypeService.update(courseType, id);
        Optional<CourseType> find = courseTypeService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).
                body(new SuccessResponse<>("Updated", find));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable Long id){
        courseTypeService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).
                body(new SuccessResponse<>("Success Delete", id));

    }
    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable Long id){
        Optional<CourseType> result =  courseTypeService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).
                body(new SuccessResponse<>("Success", result));


    }
}
