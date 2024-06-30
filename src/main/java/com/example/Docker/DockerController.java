package com.example.Docker;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
    public ResponseEntity<User> postUser(@Valid @RequestBody User user, BindingResult bindingResult){
        if (!bindingResult.hasErrors()) {
            return new ResponseEntity<>(userRepository.save(user), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/test")
    public String tests(){
        return "test";
    }
}
