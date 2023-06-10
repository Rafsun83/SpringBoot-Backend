package com.javaprojects.springbootbackend.controller;

import com.javaprojects.springbootbackend.excption.ResourceNotFoundException;
import com.javaprojects.springbootbackend.model.Course;
import com.javaprojects.springbootbackend.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/courses")
public class CourseController {

    @Autowired
    CourseRepository courseRepository;

    @GetMapping
    public List<Course> getAllCourse(){
        return courseRepository.findAll();
    }

    //create course build api
    @PostMapping
    public Course createCourse(@RequestBody Course course){
        return courseRepository.save(course);
    }

    //get course by id
    @GetMapping("{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable long id){
        Course course = courseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("course not found" + id));
        return ResponseEntity.ok(course);
    }

    //update course in REST API

    @PutMapping("{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable long id, @RequestBody Course courseDetails){
        Course course = courseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("This course are not exist" + id));
        course.setTitle(courseDetails.getTitle());
        course.setDescription(courseDetails.getDescription());
        course.setPrice(courseDetails.getPrice());
        courseRepository.save((course));
       return ResponseEntity.ok(course);
    }

    //delete course in REST API
    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteCourse(@PathVariable long id){
        Course course = courseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("This course are not Exists" + id));
        courseRepository.delete(course);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
