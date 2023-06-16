package com.javaprojects.springbootbackend.controller;

import com.javaprojects.springbootbackend.excption.ResourceNotFoundException;
import com.javaprojects.springbootbackend.model.Blog;
import com.javaprojects.springbootbackend.repository.BlogRepository;
import com.javaprojects.springbootbackend.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/blogs")
public class BlogController {

    @Autowired
    BlogRepository blogRepository;


    @GetMapping
    public List<Blog> getAllBlog(){
        return blogRepository.findAll();
    }

    @PostMapping
    public Blog createBlog(@RequestBody Blog blog)  {
        return blogRepository.save(blog);

    }

    @GetMapping("{id}")
    public ResponseEntity<Blog> getBlogById(@PathVariable long id){
        Blog blog = blogRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("This Blog is not exist here" + id));
        return ResponseEntity.ok(blog);
    }

    @PutMapping("{id}")
    public ResponseEntity<Blog> updateBlog(@PathVariable long id,@RequestBody Blog blogDetails){
        Blog blog = blogRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("This Blog is Exits note here" + id));
        blog.setTitle(blogDetails.getTitle());
        blog.setDescription(blogDetails.getDescription());
        blog.setFiles(blogDetails.getFiles());
        blogRepository.save(blog);
        return ResponseEntity.ok(blog);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteBlog(@PathVariable long id){
        Blog blog = blogRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("This is n ot exist" + id));
        blogRepository.delete(blog);
        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
