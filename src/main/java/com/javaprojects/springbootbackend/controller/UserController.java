package com.javaprojects.springbootbackend.controller;

import com.javaprojects.springbootbackend.excption.ApiResponse;
import com.javaprojects.springbootbackend.model.User;
import com.javaprojects.springbootbackend.excption.RequiredErrorResponse;
import com.javaprojects.springbootbackend.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {



    @Autowired
    private UserService userService;

//    public UserController(UserService userService) {
//        this.userService = userService;
//    }


    @GetMapping
    public List<User> getAllUser(){
        return userService.getAllUsersService();
//        return userRepository.findAll();
    }

    //create user build api
    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody User user, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            List<RequiredErrorResponse> error = bindingResult.getFieldErrors().stream().map(fieldError -> new RequiredErrorResponse(fieldError.getField(), fieldError.getDefaultMessage())).collect(Collectors.toList());
            return ResponseEntity.badRequest().body(error);
        }
        User user1 = userService.createUserService(user).getBody();
        return ResponseEntity.ok(user1);
    }

    //get user by id
    @GetMapping("{id}")
    public ResponseEntity<User> getUserById(@PathVariable long id){
        User user = userService.getUserByIdService(id).getBody();
        return ResponseEntity.ok(user);
    }

    //update user in Rest API
    @PutMapping("{id}")
    public ResponseEntity<?> updateUser(@PathVariable long id, @Valid @RequestBody User userDetails, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            List<RequiredErrorResponse> error = bindingResult.getFieldErrors().stream().map(fieldError -> new RequiredErrorResponse(fieldError.getField(), fieldError.getDefaultMessage())).collect(Collectors.toList());
            System.out.print(error);
            return ResponseEntity.badRequest().body(error);
        }
        userService.updateUserService(id, userDetails);
        ApiResponse apiResponse = new ApiResponse(200, "User updated successfully");
        return ResponseEntity.ok(apiResponse);
    }

    //delete user in REST API
    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable long id){
        userService.deleteUserService(id);
        ApiResponse apiResponse = new ApiResponse(200, "User deleted successfully");
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/name/{name}")
    public List<User> getUsersByName(@PathVariable String name){
        return userService.getUserByFirstName(name);
    }


}
