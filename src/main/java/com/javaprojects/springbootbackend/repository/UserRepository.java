package com.javaprojects.springbootbackend.repository;

import com.javaprojects.springbootbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    //all crud operation
    //get All users
    @Query(value = "SELECT * FROM users", nativeQuery = true)
    List<User> getAllUsers();

    //get User By Name
    @Query(value = "SELECT * FROM users u WHERE u.first_name=:name", nativeQuery = true)
    List<User> findAllByFirstName(String name);

}
