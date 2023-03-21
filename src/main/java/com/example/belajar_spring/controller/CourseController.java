package com.example.belajar_spring.controller;

import com.example.belajar_spring.model.Course;
import com.example.belajar_spring.model.request.CourseRequest;
import com.example.belajar_spring.model.response.ErrorResponse;
import com.example.belajar_spring.model.response.SuccessResponse;
import com.example.belajar_spring.service.ICourseService;
import com.example.belajar_spring.utils.CourseKey;
import com.example.belajar_spring.utils.JwtUtil;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/courses")
public class CourseController {
    @Autowired
    private ICourseService courseService;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping
    public ResponseEntity getAllCourse(){
        List<Course> courseList = courseService.list();
        String token = jwtUtil.generateToken("Enigma");
        System.out.println("Token " + token);
        return ResponseEntity.status(HttpStatus.OK).
                body(new SuccessResponse<List<Course>>("Success", courseList));

    }
    @PostMapping
    public ResponseEntity createCourse(@Valid @RequestBody CourseRequest course){
        Course newCourse = modelMapper.map(course, Course.class);
        Course result = courseService.create(newCourse);
        return ResponseEntity.status(HttpStatus.CREATED).
                body(new SuccessResponse<>("Created", result));

    }
    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable String id){
        Optional<Course> course = courseService.get(id);
        return ResponseEntity.status(HttpStatus.OK).
                body(new SuccessResponse<>("Success", course));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable String id){
        courseService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).
                body(new SuccessResponse<String>("Success Delete", id));
    }
    @PatchMapping("/{id}")
    public ResponseEntity edit(@RequestBody Course course,@PathVariable String id){
        courseService.update(course, id);
        Optional<Course> find = courseService.get(course.getCourseId());
        return ResponseEntity.status(HttpStatus.OK).
                body(new SuccessResponse<Optional<Course>>("Updated", find));

    }
    @GetMapping(params = {"key", "value"})
    public ResponseEntity getBy(@RequestParam String key, @RequestParam String value) {
        Optional<List<Course>> courses = courseService.findBy(CourseKey.valueOf(key), value);
        System.out.println(courses);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<Optional<List<Course>>>("Success", courses));
    }
}
