package net.reelmovies.sunbreakers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
// import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

// Runs program and added a random to MongoDB when ran
@SpringBootApplication
@RestController
public class SpringtestApplication implements CommandLineRunner 
{

	public static void main(String[] args) 
	{
		// TODO: use recommended get method when user likes a movie
		// TODO: add questionnaire when first signing up
		// TODO: fix spacing between login and info slides buttons
		// TODO: move login button to movies page
		// TODO: add film age rating
		// TODO: remove extra try catch statements
		// TODO: comment code
		SpringApplication.run(SpringtestApplication.class, args);
	}

	@Autowired
	private MovieRepository movieRepository;

	@Override
	public void run(String... args) throws Exception 
	{
		Movie movie = new Movie();
        ParseMovies m = new ParseMovies();
		int randomMovieFromDiscover = ApiGetMethod.getMovieFromDiscover();
        m.setMovie(randomMovieFromDiscover);

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
	// CommandLineRunner runner(MovieRepository repository) 
	// {
	// 	return args -> 
	// 	{
	// 		Movie movie = new Movie(
	// 			generateRandInt(),
	// 			generateRandInt(),
	// 			generateRandInt(),
	// 			generateRandInt()
				
	// 		);

	// 		repository.insert(movie);
	// 	};
	// }

	// public static String generateRandInt()
	// {
	// 	Random random = new Random();
	// 	int randomNum = Math.abs(random.nextInt());
	// 	String t = Integer.toString(randomNum);
	// 	return t;
	// }
}