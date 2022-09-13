package com.example.fastestfing.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fastestfing.model.User;

public interface UserRepo extends JpaRepository <User,Integer> {
    User findByRanking(String ranking);
    void deleteByRanking(String ranking);
    void deleteByScore(int score);
}
