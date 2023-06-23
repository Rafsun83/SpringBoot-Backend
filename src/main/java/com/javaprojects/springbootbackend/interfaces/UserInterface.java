package com.javaprojects.springbootbackend.interfaces;


import com.javaprojects.springbootbackend.model.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserInterface {

    List<User> getAllUsersService();
    ResponseEntity<User> createUserService(User user);
    ResponseEntity<User> getUserByIdService(long id);
    void updateUserService(long id, User user);
    void deleteUserService(long id);
    List<User> getUserByFirstName(String name);



}
