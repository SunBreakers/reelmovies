package com.example.springtest;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;

import com.uwetrottmann.tmdb2.Tmdb;
import com.uwetrottmann.tmdb2.entities.Movie;
import com.uwetrottmann.tmdb2.entities.Genre;
import com.uwetrottmann.tmdb2.services.MoviesService;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.io.IOException;

@Controller
public class parseMovies{
    static String poster_path;
    static String movieTitle;
    static String movieOverview;
    static List<Genre> movieGenres; // FIX: Printing Genres
    // static String movieGenres; // FIX: Printing Genres
    static int movieRuntime;
    static Date movieReleaseDate;
    static double movieVoteAverage;
    static int movieVoteCount;
    static String imdb_id;
    // static String movieRecommendations;
    // static String movieVideo;

    // https://www.themoviedb.org/documentation/api
    // https://github.com/UweTrottmann/tmdb-java
    public static void getMoviesFromAPI(){
        Random random = new Random();
        int randomMovie = random.nextInt(812104) + 1;

        Tmdb tmdb = new Tmdb("5ae9bfda7c93c18a70125da1d0f9cb7d"); // Insert TheMovieDatabase API Key Here
        MoviesService moviesService = tmdb.moviesService(); 
        try {
            retrofit2.Response<Movie> response = moviesService
                .summary(randomMovie, "") // 550 = Fight Club
                .execute();
            if (response.isSuccessful()) {
                Movie movie = response.body();
                System.out.println("Testing movie: (" + randomMovie + ") " + movie.title);
                while(movie == null || movie.adult == true || movie.poster_path == null){ // prevents null, adult movies or movies without posters from showing
                    randomMovie = random.nextInt(812104) + 1;
                    response = moviesService
                        .summary(randomMovie, "")
                        .execute();
                    movie = response.body();
                    if(movie != null)
                        System.out.println("Testing movie: (" + randomMovie + ") " + movie.title);
                }
                poster_path = movie.poster_path;
                movieTitle = movie.title;
                movieOverview = movie.overview;
                movieGenres = movie.genres; // FIX: Printing Genres
                movieRuntime = movie.runtime;
                movieReleaseDate = movie.release_date;
                movieVoteAverage = movie.vote_average;
                // movieVoteAverage = movie.rating;
                movieVoteCount = movie.vote_count;
                imdb_id = movie.imdb_id;
                // movieRecommendations = movie.recommendations.toString();
                // movieRecommendations = movie.similar.toString();
                // movieVideo = movie.videos.toString();
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
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