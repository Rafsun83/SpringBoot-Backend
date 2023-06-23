package com.javaprojects.springbootbackend.interfaces;

import com.javaprojects.springbootbackend.model.Course;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CourseInterface {

     List<Course> getAllCourseService();
     
     void createPostService(Course course);
     ResponseEntity<Course> getCourseByIdService(long id);
     void updateCourseService(long id, Course course);
     void deleteCourse(long id);
}
