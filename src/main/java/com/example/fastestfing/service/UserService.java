package com.example.fastestfing.service;

import java.util.List;

import com.example.fastestfing.model.User;

public interface UserService {
    User saveUser(User user);
    List<User> getRanking();
    User getRankDetails(String name, int score);
    
    
}
