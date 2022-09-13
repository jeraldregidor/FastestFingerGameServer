package com.example.fastestfing.service;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.fastestfing.model.User;
import com.example.fastestfing.repository.UserRepo;

import lombok.RequiredArgsConstructor;

@Service @RequiredArgsConstructor @Transactional
public class UserServiceImp implements UserService {

    private final UserRepo userRepo;

    @Override
    public User saveUser(User user) {
        return userRepo.save(user);
    }

    @Override
    public List<User> getRanking() {
        // TODO Auto-generated method stub
        return userRepo.findAll(Sort.by(Sort.Direction.DESC,"score"));
    }

    @Override
    public User getRankDetails(String name, int score) {
        List<User> participants = userRepo.findAll();
        int beatenParticipants = 0;
        for (User participant : participants) {
            if(participant.getScore() < score){
                beatenParticipants++;
                int newRankingInt = Integer.parseInt(participant.getRanking())+1;
                participant.setRanking(Integer.toString(newRankingInt));
            }
        }

        {/*This loop will delete rank 11 and above*/}
        User userRank10 = userRepo.findByRanking("10");
        int scoreRank10 = userRank10.getScore();
        userRepo.deleteByRanking("11"); // incase of equal to rank 10
        for (User participant : participants) {
            if(participant.getScore() < scoreRank10){
                userRepo.deleteByScore(participant.getScore());
            }
        }


        if(beatenParticipants < 1){
           return new User(0, "No Ranking", name, score);
        }
        int rankingInt = 11-beatenParticipants;

        User userDetails = new User();
        userDetails.setName(name);
        userDetails.setScore(score);
        userDetails.setRanking(Integer.toString(rankingInt));

        userRepo.save(userDetails);

        return userDetails;
    }

    
}
