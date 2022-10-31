package com.example.springtest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;
import java.util.Random;

@SpringBootApplication
@RestController
public class SpringtestApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringtestApplication.class, args);
	}

	@Autowired
	private MovieRepository movieRepository;

	@Override
	public void run(String... args) throws Exception {
		// TODO: Do while this.movie.id == null
		Movie movie = new Movie();
		Random random = new Random();
        int randomMovie = random.nextInt(812104) + 1;
        parseMovies m = new parseMovies();
        m.setMovie(randomMovie);

		movie.setPosterPath(m.getPosterPath());
		movie.setTitle(m.getTitle());
		movie.setOverview(m.getOverview());
		movie.setGenres(m.getGenres());
		movie.setRuntime(m.getRuntime());
		movie.setReleaseDate(m.getReleaseDate());
		movie.setVoteAverage(m.getVoteAverage());
		movie.setVoteCount(m.getVoteCount());
		movie.setId(m.getIMDB_ID());
		movieRepository.save(movie);
		System.out.println("Added movie: (" + m.getID() + ") " + m.getTitle() + " to MongoDB\n");
	}

	// @Bean
	// CommandLineRunner runner(MovieRepository repository) {
	// 	return args -> {
	// 		Movie movie = new Movie(
	// 			generateRandInt(),
	// 			generateRandInt(),
	// 			generateRandInt(),
	// 			generateRandInt()
				
	// 		);

	// 		repository.insert(movie);
	// 	};
	// }

	// public static String generateRandInt(){
	// 	Random random = new Random();
	// 	int randomNum = Math.abs(random.nextInt());
	// 	String t = Integer.toString(randomNum);
	// 	return t;
	// }
}