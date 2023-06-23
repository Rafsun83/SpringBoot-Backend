package com.javaprojects.springbootbackend.service;


import com.javaprojects.springbootbackend.excption.ResourceNotFoundException;
import com.javaprojects.springbootbackend.interfaces.BlogInterface;
import com.javaprojects.springbootbackend.model.Blog;
import com.javaprojects.springbootbackend.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService implements BlogInterface {

    @Autowired
    private BlogRepository blogRepository;

    //getAllBlog Service
    @Override
    public List<Blog> getAllBlogService(){
        return blogRepository.findAll();
    }

    //crate blog service
    @Override
    public void createBlogService(Blog blog){
        Blog blog1 = blogRepository.save(blog);
        ResponseEntity.ok(blog1);
    }

    //getBlogById service
    @Override
    public ResponseEntity<Blog> getBlogById(long id){
        Blog blog = blogRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("This Blog is not exist here" + id));
        return ResponseEntity.ok(blog);
    }

    //updateBlog Service
    @Override
    public void updateBlogService(long id, Blog blogDetails){
        Blog blog = blogRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("This Blog is Exits note here" + id));
        blog.setTitle(blogDetails.getTitle());
        blog.setDescription(blogDetails.getDescription());
        blog.setFiles(blogDetails.getFiles());
        blogRepository.save(blog);
        ResponseEntity.ok(blog);
    }

    public void deleteBlogService(long id){
        Blog blog = blogRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("This is n ot exist" + id));
        blogRepository.delete(blog);
    }

}
