package com.javaprojects.springbootbackend.repository;

import com.javaprojects.springbootbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    //all crud operation
}
