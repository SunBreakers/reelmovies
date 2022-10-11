package com.example.springtest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MovieController {

	@GetMapping("/movies")
	public String movies(String movies, Model model) {
		parseMovies.getMoviesFromAPI();
        model.addAttribute("poster_path", "https://www.themoviedb.org/t/p/w300_and_h450_bestv2" + parseMovies.poster_path);
        model.addAttribute("movieTitle", parseMovies.movieTitle);
        model.addAttribute("movieOverview", parseMovies.movieOverview);
        model.addAttribute("movieGenres", parseMovies.movieGenres); // FIX: Printing Genres
        model.addAttribute("movieRuntime", parseMovies.movieRuntime);
        model.addAttribute("movieReleaseDate", parseMovies.movieReleaseDate);
        model.addAttribute("movieVoteAverage", parseMovies.movieVoteAverage);
        model.addAttribute("movieVoteCount", parseMovies.movieVoteCount);
        model.addAttribute("imdb_id", "https://www.imdb.com/title/" + parseMovies.imdb_id + "/");
        // model.addAttribute("movieRecommendations", parseMovies.movieRecommendations);
        // model.addAttribute("movieVideo", parseMovies.movieVideo);
		return "movies";
	}
}