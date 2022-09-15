package com.example.fastestfing.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.fastestfing.model.User;
import com.example.fastestfing.service.UserService;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "*", allowedHeaders = "*") 
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    
    @GetMapping("/getParticipants")
    public List<User> getParticipants(){
        return userService.getRanking();
    }

    @PostMapping("/computeRanking")
    public User computeRanking(@RequestBody User challengerForm){
        return userService.getRankDetails(challengerForm.getName(), challengerForm.getScore());
    }
}
