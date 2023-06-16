package com.javaprojects.springbootbackend.controller;

import com.javaprojects.springbootbackend.excption.ResourceNotFoundException;
import com.javaprojects.springbootbackend.model.User;
import com.javaprojects.springbootbackend.repository.UserRepository;
import com.javaprojects.springbootbackend.excption.RequiredErrorResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;


    @GetMapping
    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    //create user build api
    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody User user, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            List<RequiredErrorResponse> error = bindingResult.getFieldErrors().stream().map(fieldError -> new RequiredErrorResponse(fieldError.getField(), fieldError.getDefaultMessage())).collect(Collectors.toList());
            return ResponseEntity.badRequest().body(error);
        }
        User user1 = userRepository.save(user);
        return ResponseEntity.ok(user1);
    }

    //get user by id
    @GetMapping("{id}")
    public ResponseEntity<User> getUserById(@PathVariable long id){
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not Exits" + id));
        return ResponseEntity.ok(user);
    }

    //update user in Rest API
    @PutMapping("{id}")
    public ResponseEntity<User> updateUser(@PathVariable long id, @RequestBody User userDetails){
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not exits" + id));
        user.setFirstName(userDetails.getFirstName());
        user.setLastName(userDetails.getLastName());
        user.setEmail(userDetails.getEmail());
        user.setAddress(userDetails.getAddress());
        user.setLocation(userDetails.getLocation());
        userRepository.save(user);
        return ResponseEntity.ok(user);
    }

    //delete user in REST API
    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable long id){
        User user = userRepository.findById(id).orElseThrow(() ->new ResourceNotFoundException("This user not exist here: " + id));
        userRepository.delete(user);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
