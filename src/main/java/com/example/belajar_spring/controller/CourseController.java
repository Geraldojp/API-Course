package com.example.belajar_spring.controller;

import com.example.belajar_spring.model.Course;
import com.example.belajar_spring.model.CourseInfo;
import com.example.belajar_spring.model.CourseType;
import com.example.belajar_spring.model.request.CourseRequest;
import com.example.belajar_spring.model.response.SuccessResponse;
import com.example.belajar_spring.service.CourseInfoService;
import com.example.belajar_spring.service.CourseService;
import com.example.belajar_spring.service.CourseTypeService;
import com.example.belajar_spring.utils.constatns.CourseKey;
import com.example.belajar_spring.utils.constatns.FindOperator;
import com.example.belajar_spring.utils.specification.SearchCriteria;
import com.example.belajar_spring.utils.validation.JwtUtil;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/courses")
public class CourseController {
    @Autowired
    private CourseService courseService;
    @Autowired
    private CourseInfoService courseInfoService;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CourseTypeService courseTypeService;
    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping
    public ResponseEntity getAllCourse(int page, String sort){
        Pageable pageable = PageRequest.of(page, 10, Sort.by("courseId").ascending());
        if(sort.equalsIgnoreCase("desc")){
            pageable = PageRequest.of(page, 10, Sort.by("courseId").descending());
        }
        Iterable<Course> courseList = courseService.findAll(pageable);
        return ResponseEntity.status(HttpStatus.OK).
                body(new SuccessResponse<>("Success", courseList));

    }
    @PostMapping
    public ResponseEntity createCourse(@Valid @RequestBody CourseRequest course){
        Course result = courseService.create(course);
        return ResponseEntity.status(HttpStatus.CREATED).
                body(new SuccessResponse<>("Created", result));

    }
    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable Long id){
        Optional<Course> course = courseService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).
                body(new SuccessResponse<>("Success", course));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        courseService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).
                body(new SuccessResponse<>("Success Delete", id));
    }
    @PatchMapping("/{id}")
    public ResponseEntity edit(@RequestBody Course course,@PathVariable Long id){
        courseService.update(course, id);
        Optional<Course> find = courseService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).
                body(new SuccessResponse<>("Updated", find));

    }
    @GetMapping(params = {"key", "value", "operator"})
    public ResponseEntity getAllBy(@RequestParam("key") String key, @RequestParam("value") String value, @RequestParam("operator") String operator) throws Exception {
        SearchCriteria searchCriteria = new SearchCriteria(key, FindOperator.valueOf(operator), value);
        List<Course> courses = courseService.listBy(searchCriteria);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success get all course by", courses));
    }
}
