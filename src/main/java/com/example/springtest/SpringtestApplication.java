package com.example.springtest;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;
import java.util.Random;

@SpringBootApplication
@RestController
public class SpringtestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringtestApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(MovieRepository repository) {
		return args -> {
			Movie movie = new Movie(
				generateRandInt(),
				generateRandInt(),
				generateRandInt(),
				generateRandInt()
				
			);

			repository.insert(movie);
		};
	}

	public static String generateRandInt(){
		Random random = new Random();
		int randomNum = Math.abs(random.nextInt());
		String t = Integer.toString(randomNum);
		return t;
	}
}