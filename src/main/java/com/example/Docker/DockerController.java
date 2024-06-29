package com.example.Docker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DockerController {
    @Autowired
    UserRepository userRepository;

    private DockerController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/allUsers")
    public List<User> getAllUsers(){
        return (List<User>) userRepository.findAll();
    }

    @PostMapping("/postUser")
    public ResponseEntity<User> postUser(@Validated @RequestBody User user){
        return new ResponseEntity<>(userRepository.save(user), HttpStatus.OK);
    }

    @GetMapping("/test")
    public String tests(){
        return "test";
    }
}
