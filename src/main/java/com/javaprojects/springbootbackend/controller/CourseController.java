package com.javaprojects.springbootbackend.controller;

import com.javaprojects.springbootbackend.excption.ApiResponse;
import com.javaprojects.springbootbackend.excption.RequiredErrorResponse;
import com.javaprojects.springbootbackend.excption.ResourceNotFoundException;
import com.javaprojects.springbootbackend.model.Course;
import com.javaprojects.springbootbackend.repository.CourseRepository;
import com.javaprojects.springbootbackend.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/courses")
public class CourseController {

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    private CourseService courseService;

    @GetMapping
    public List<Course> getAllCourse(){
        return courseService.getAllCourseService();
    }

    //create course build api
    @PostMapping
    public ResponseEntity<?> createCourse(@Valid @RequestBody Course course, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            List<RequiredErrorResponse> error = bindingResult.getFieldErrors().stream().map(fieldError -> new RequiredErrorResponse(fieldError.getField(), fieldError.getDefaultMessage())).collect(Collectors.toList());
            return ResponseEntity.badRequest().body(error);
        }
        courseService.createPostService(course);
        ApiResponse apiResponse = new ApiResponse(200, "Course Created Successfully");
        return ResponseEntity.ok(apiResponse);
    }

    //get course by id
    @GetMapping("{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable long id){
//        Course course = courseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("course not found" + id));
        ResponseEntity<Course> course = courseService.getCourseByIdService(id);
        return ResponseEntity.ok(course.getBody());
    }

    //update course in REST API

    @PutMapping("{id}")
    public ResponseEntity<?> updateCourse(@PathVariable long id, @RequestBody Course courseDetails){
        courseService.updateCourseService(id, courseDetails);
        ApiResponse apiResponse = new ApiResponse(200, "Course Updated Successfully");
       return ResponseEntity.ok(apiResponse);
    }

    //delete course in REST API
    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponse> deleteCourse(@PathVariable long id){
//        Course course = courseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("This course are not Exists" + id));
//        courseRepository.delete(course);
        courseService.deleteCourse(id);
        ApiResponse apiResponse = new ApiResponse(200, "Course deleted successfully!!");
        return ResponseEntity.ok(apiResponse);
    }
}
