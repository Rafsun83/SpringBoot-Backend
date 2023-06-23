package com.javaprojects.springbootbackend.service;

import com.javaprojects.springbootbackend.excption.ResourceNotFoundException;
import com.javaprojects.springbootbackend.interfaces.UserInterface;
import com.javaprojects.springbootbackend.model.User;
import com.javaprojects.springbootbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class UserService implements UserInterface {

//    private final UserService userService;
//   public UserService( UserService userService){
//        this.userService = userService;
//   }

    @Autowired
    private UserRepository userRepository;


    //getAllUsers service
    @Override
    public List<User> getAllUsersService(){
        return userRepository.findAll();
    }
    //create user service
    @Override
    public ResponseEntity<User> createUserService(User user){
        User user1 = userRepository.save(user);
        return ResponseEntity.ok(user1);
    }

    //getUser byId service
    @Override
    public ResponseEntity<User> getUserByIdService(long id){
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not Exits" + id));
        return ResponseEntity.ok(user);
    }
    
    //update user service
    @Override
    public void updateUserService(@PathVariable long id, User userDetails ){
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not exits" + id));
        user.setFirstName(userDetails.getFirstName());
        user.setLastName(userDetails.getLastName());
        user.setEmail(userDetails.getEmail());
        user.setPassword(userDetails.getPassword());
        user.setAddress(userDetails.getAddress());
        user.setLocation(userDetails.getLocation());
        userRepository.save(user);
        ResponseEntity.ok(user);

    }

    //deleted user service
    @Override
    public void deleteUserService(long id){
        User user = userRepository.findById(id).orElseThrow(() ->new ResourceNotFoundException("This user not exist here: " + id));
        userRepository.delete(user);
//        new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        return ResponseEntity.ok("user delete successfully");
}


    //Get User by FirstName Service
    @Override
    public List<User> getUserByFirstName(String name) {
        return userRepository.findAllByFirstName(name);
    }
}
