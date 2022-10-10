package com.example.springtest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MovieController {

	@GetMapping("/movies")
	public String movies(String movies, Model model) {
        model.addAttribute("movieData", parseMovies.getMoviesFromAPI());
		model.addAttribute("movieData", parseMovies.getMoviesFromAPI());
		return "movies";
	}
}