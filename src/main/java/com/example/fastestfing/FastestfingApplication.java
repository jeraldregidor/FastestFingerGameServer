package com.example.fastestfing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.fastestfing.model.User;
import com.example.fastestfing.repository.UserRepo;
import com.example.fastestfing.service.UserService;

@SpringBootApplication
public class FastestfingApplication {
	@Autowired
	UserRepo userRepo;

	public static void main(String[] args) {
		SpringApplication.run(FastestfingApplication.class, args);
	}
	
	@Bean
	CommandLineRunner run(UserService userService){
		return args -> {	
			if(userRepo.findAll().isEmpty()){	
				for(int x = 0; x<=9 ; x++){
					String ranking = Integer.toString(x + 1);
					String name = "name" + ranking ;
					int score = (11-Integer.parseInt(ranking))*10;	
					userService.saveUser(new User(Integer.parseInt(ranking),ranking,name,score));	
				}
			}
		};
	}

}
