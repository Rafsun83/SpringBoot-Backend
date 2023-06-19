package com.javaprojects.springbootbackend.service;

import com.javaprojects.springbootbackend.model.User;
import com.javaprojects.springbootbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<User> createUserService(User user){

        User user1 = userRepository.save(user);
        return ResponseEntity.ok(user1);

    }
}
