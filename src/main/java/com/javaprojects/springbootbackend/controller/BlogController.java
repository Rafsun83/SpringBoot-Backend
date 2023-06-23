package com.javaprojects.springbootbackend.controller;

import com.javaprojects.springbootbackend.excption.ApiResponse;
import com.javaprojects.springbootbackend.excption.RequiredErrorResponse;
import com.javaprojects.springbootbackend.excption.ResourceNotFoundException;
import com.javaprojects.springbootbackend.model.Blog;
import com.javaprojects.springbootbackend.repository.BlogRepository;
import com.javaprojects.springbootbackend.service.BlogService;
import com.javaprojects.springbootbackend.service.FileService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/blogs")
public class BlogController {

    @Autowired
    BlogRepository blogRepository;

    @Autowired
    private BlogService blogService;


    @GetMapping
    public List<Blog> getAllBlog(){
        return blogService.getAllBlogService();
    }

    //CreateBlog
    @PostMapping
    public ResponseEntity<?> createBlog(@Valid @RequestBody Blog blog, BindingResult bindingResult)  {
        if (bindingResult.hasErrors()){
            List<RequiredErrorResponse> error = bindingResult.getFieldErrors().stream().map(fieldError -> new RequiredErrorResponse(fieldError.getField(), fieldError.getDefaultMessage())).collect(Collectors.toList());
            return ResponseEntity.badRequest().body(error);
        }
//        ResponseEntity<?> blog1 = blogService.createBlogService(blog);
//        return ResponseEntity.ok(blog1);
        blogService.createBlogService(blog);
        ApiResponse apiResponse = new ApiResponse(200, "Blog Created Successfully");
        return ResponseEntity.ok(apiResponse);

    }

    //getBlog by id
    @GetMapping("{id}")
    public ResponseEntity<Blog> getBlogById(@PathVariable long id){
        ResponseEntity<Blog> blog = blogService.getBlogById(id);
        return ResponseEntity.ok(blog.getBody());
    }

    //update blog
    @PutMapping("{id}")
    public ResponseEntity<?> updateBlog(@PathVariable long id,@RequestBody Blog blogDetails){
        blogService.updateBlogService(id, blogDetails);
        ApiResponse apiResponse = new ApiResponse(200, "Blog Updated Successfully");
        return ResponseEntity.ok(apiResponse);
    }

    //Delete Blog
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteBlog(@PathVariable long id){
        blogService.deleteBlogService(id);
        ApiResponse apiResponse = new ApiResponse(200, "Blog delete successfully");
        return  ResponseEntity.ok(apiResponse);
    }
}
