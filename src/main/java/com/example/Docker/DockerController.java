package com.example.Docker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DockerController {
    @Autowired
    UserRepository userRepository;
    private DockerController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public String home(){
        return "hi";
    }

    @GetMapping("/allUsers")
    public List<User> getAllUsers(){
        return (List<User>) userRepository.findAll();
    }

    @PostMapping("/postUser")
    public User postUser(@RequestBody User user){
        return userRepository.save(user);
    }

    @GetMapping("/test")
    public String tests(){
        return "test";
    }


}

