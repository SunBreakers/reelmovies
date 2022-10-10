package com.example.springtest;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;

import java.util.Random;

import com.uwetrottmann.tmdb2.Tmdb;
import com.uwetrottmann.tmdb2.entities.Movie;
import com.uwetrottmann.tmdb2.services.MoviesService;

import java.io.IOException;

@Controller
public class parseMovies{    
    // https://www.themoviedb.org/documentation/api
    // https://github.com/UweTrottmann/tmdb-java
    public static String getMoviesFromAPI(){
        StringBuilder str = new StringBuilder();
        Random random = new Random();
        int randomMovie = random.nextInt(812104) + 1;

        Tmdb tmdb = new Tmdb("API_KEY"); // Insert TheMovieDatabase API Key Here
        MoviesService moviesService = tmdb.moviesService(); 
        try {
            retrofit2.Response<Movie> response = moviesService
                .summary(randomMovie, "") // 550 = Fight Club
                .execute();
            if (response.isSuccessful()) {
                Movie movie = response.body();
                str.append("Poster Path: https://www.themoviedb.org/t/p/w300_and_h450_bestv2" + movie.poster_path);
                str.append("<br>Title: " + movie.title);
                str.append("<br>Overview: " + movie.overview);
                str.append("<br>Genres: " + movie.genres.toString()); // FIX 
                str.append("<br>Runtime: " + movie.runtime + " minutes");
                str.append("<br>Release Date: " + movie.release_date);
                str.append("<br>Vote Average: " + movie.vote_average + "/10");
                str.append("<br>Vote Count: " + movie.vote_count + " votes");
                str.append("<br>IMDB ID: https://www.imdb.com/title/" + movie.imdb_id);
                System.out.println(str);
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return str.toString();
    }

    public static String jsoupParse(){
        StringBuilder str = new StringBuilder();
        try{
            Document doc = Jsoup.connect("https://www.google.com").get();

            // get page title
            String title = doc.title();
            str.append("title : " + title);

            // get all links
            Elements links = doc.select("a[href]");
            for (Element link : links) {
                str.append("<br>link : " + link.attr("href"));
                str.append("<br>text : " + link.text());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return str.toString();
    }
}