package com.javaprojects.springbootbackend.interfaces;

import com.javaprojects.springbootbackend.model.Blog;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BlogInterface {
    List<Blog> getAllBlogService();
    void createBlogService(Blog blog);
    ResponseEntity<Blog> getBlogById(long id);
    void updateBlogService(long id, Blog blog);
    void deleteBlogService(long id);
}
