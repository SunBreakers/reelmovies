package com.example.springtest;

import java.io.IOException;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {
	// Julian Rowe
	// @GetMapping("/moviesTest")
	// public String getMoviesFromAPI(@RequestParam(name="movies", required=false) String movies, Model model) {
	// 	model.addAttribute("movies", movies);
	// 	return parseMovies.getMoviesFromAPI();
	// }
	@GetMapping("/jsoup")
	public String getMovies(@RequestParam(name="jsoup", required=false) String jsoup, Model model) {
		model.addAttribute("jsoup", jsoup);
		return parseMovies.jsoupParse();
	}

	@GetMapping("/predicate")
	public String getICE(@RequestParam(name="predicate", required=false) String predicate, Model model) {
		model.addAttribute("predicate", predicate);
		return PredicateEx.getICE();
	}
	
	//Duc Thanh Nguyen
	 @GetMapping("/hi")
	 public String hi(@RequestParam(value = "name", defaultValue = "a good day!!!") String name) {
	 	return String.format("Haveeeeee %s!", name);
	 }
	
	//Noe Rivera
	@GetMapping("/aboutus")
	public String aboutus(@RequestParam(value = "name", defaultValue = "get to know our team!") String name) 
	{
		return String.format("Welcome future SunBreaker, %s!", name);
	}

	//Ryan Mercado
	@GetMapping("/RandomNumber")
	public double RandomNumber() 
	{
		return RanNum.calculate();
	}
	/* @GetMapping("/case")
	public String getCasing(@RequestParam(name="casing", required=false) String casing, Model model) throws IOException {
		return commonsIOEX.getCase();
	} */
	
	//Tyler Kloss
	@GetMapping("/howdy")
	public String howdy(@RequestParam(value = "name", defaultValue = "Partner") String name) {
		return String.format("Howdy %s!", name);
	}
	@GetMapping("/test")
	public String getTest(@RequestParam(name="test", required=false) String test, Model model) {
		model.addAttribute("test", test);
		return clojureTest.clojure();
	}
}
