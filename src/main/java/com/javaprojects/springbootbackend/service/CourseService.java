package com.javaprojects.springbootbackend.service;

import com.javaprojects.springbootbackend.excption.ResourceNotFoundException;
import com.javaprojects.springbootbackend.interfaces.CourseInterface;
import com.javaprojects.springbootbackend.model.Course;
import com.javaprojects.springbootbackend.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService implements CourseInterface {

    @Autowired
    private CourseRepository courseRepository;

    //get all course service
    @Override
    public List<Course> getAllCourseService(){
       List<Course> courses;
       courses = courseRepository.findAll();
       return courses;
    }

    //create post service
    @Override
    public void createPostService(Course course){
        Course course1 = courseRepository.save(course);
        ResponseEntity.ok(course1);
    }

    //getCourseByIdService
    @Override
    public ResponseEntity<Course> getCourseByIdService(long id){
        Course course = courseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("course not found" + id));
        return ResponseEntity.ok(course);
    }


    //updateCourseService
    @Override
    public void updateCourseService(long id, Course courseDetails){
        Course course = courseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("This course are not exist" + id));
        course.setTitle(courseDetails.getTitle());
        course.setDescription(courseDetails.getDescription());
        course.setPrice(courseDetails.getPrice());
        course.setFiles(courseDetails.getFiles());
        courseRepository.save((course));
        ResponseEntity.ok(course);
    }

    //deleteCourseService
    @Override
    public void deleteCourse(long id){
        Course course = courseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("This course are not Exists" + id));
        courseRepository.delete(course);
    }
}
