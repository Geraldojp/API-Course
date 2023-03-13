package com.example.belajar_spring.controller;

import com.example.belajar_spring.model.Course;
import com.example.belajar_spring.model.response.ErrorResponse;
import com.example.belajar_spring.model.response.SuccessResponse;
import com.example.belajar_spring.service.ICourseService;
import com.example.belajar_spring.utils.CourseKey;
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
    ICourseService courseService;

    @GetMapping
    public ResponseEntity getAllCourse(){
        try {
            List<Course> courseList = courseService.list();
            return ResponseEntity.status(HttpStatus.OK).
                    body(new SuccessResponse<List<Course>>("Success", courseList));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).
                    body(new ErrorResponse("404","Empty Data"));
        }
    }
    @PostMapping
    public ResponseEntity createCourse(@RequestBody Course course){
        try {
            Course newCourse = courseService.create(course);
            return ResponseEntity.status(HttpStatus.CREATED).
                    body(new SuccessResponse<Course>("Created", newCourse));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body(new ErrorResponse("500", "Failed"));
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable String id){
        try {
            Optional<Course> course = courseService.get(id);
            return ResponseEntity.status(HttpStatus.OK).
                    body(new SuccessResponse<Optional<Course>>("Success", course));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("404", "ID Not Found"));
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable String id){
        try{
            courseService.delete(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).
                    body(new SuccessResponse<String>("Success Delete", id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body(new ErrorResponse("500", "Failed to delete"));
        }
    }
    @PatchMapping("/{id}")
    public ResponseEntity edit(@RequestBody Course course,@PathVariable String id){
        try {
            courseService.update(course, id);
            Optional<Course> find = courseService.get(course.getCourseId());
            return ResponseEntity.status(HttpStatus.OK).
                    body(new SuccessResponse<Optional<Course>>("Updated", find));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body(new ErrorResponse("400", "Failed to update"));
        }
    }
    @GetMapping(params = {"key", "value"})
    public ResponseEntity getBy(@RequestParam String key, @RequestParam String value){
        try {
            Optional<List<Course>> courses = courseService.findBy(CourseKey.valueOf(key), value);
            System.out.println(courses);
            return ResponseEntity.status(HttpStatus.OK).
                    body(new SuccessResponse<Optional<List<Course>>>("Success", courses));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).
                    body(new ErrorResponse("404", "No Course Found"));
        }
    }
}
