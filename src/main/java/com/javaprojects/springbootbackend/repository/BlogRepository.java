package com.javaprojects.springbootbackend.repository;

import com.javaprojects.springbootbackend.model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Blog , Long> {
}
