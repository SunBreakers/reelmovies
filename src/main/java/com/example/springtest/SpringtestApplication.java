package com.example.springtest;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

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
				"51151",
				"61452",
				"71452",
				"812542"
				
			);

			repository.insert(movie);
		};
	}
}