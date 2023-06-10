package com.javaprojects.springbootbackend.repository;

import com.javaprojects.springbootbackend.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
