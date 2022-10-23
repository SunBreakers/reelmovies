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

import java.util.List;
import java.util.Random;
import java.io.IOException;
import java.text.SimpleDateFormat;

@Controller
public class parseMovies{
    private Movie movie = new Movie();
    private static final String API_KEY = "5ae9bfda7c93c18a70125da1d0f9cb7d";
    // private String movieRecommendations;
    // private String movieVideo;

    // https://www.themoviedb.org/documentation/api
    // https://github.com/UweTrottmann/tmdb-java
    public void getMoviesFromAPI(int movieID)
    {
        Random random = new Random();
        int randomMovie = movieID;

        Tmdb tmdb = new Tmdb(API_KEY); // Insert TheMovieDatabase API Key Here
        MoviesService moviesService = tmdb.moviesService(); 
        try 
        {
            retrofit2.Response<Movie> response = moviesService
                .summary(randomMovie, "") // 550 = Fight Club
                .execute();
            if (response.isSuccessful()) 
            {
                this.movie = response.body();
                System.out.println("Testing movie: (" + randomMovie + ") " + this.movie.title);
                while(this.movie == null || this.movie.adult == true || this.movie.poster_path == null || this.movie.imdb_id == null || this.movie.runtime == null || this.movie.release_date == null) // prevents null, adult movies or movies without posters from showing
                {
                    randomMovie = random.nextInt(812104) + 1;
                    response = moviesService
                        .summary(randomMovie, "")
                        .execute();
                        this.movie = response.body();
                    if(movie != null)
                    {
                        System.out.println("Testing movie: (" + randomMovie + ") " + this.movie.title);
                    }
                }
            }
        } 
        catch (Exception e) 
        {
            System.out.println("Exception: " + e);
        }
    }

    public void setMovie(int newMovie) {
        getMoviesFromAPI(newMovie);
        this.movie.id = movie.id;
    }

    public String getPosterPath() {
        return this.movie.poster_path;
    }

    public String getTitle() {
        return this.movie.title;
    }

    public String getOverview() {
        return this.movie.overview;
    }

    public String getGenres() {
        String genreList = "";
        for (Genre genre : this.movie.genres) {
            if (genreList == "") {
                genreList = genreList + genre.name;
            }
            else {
                genreList = genreList + ", " + genre.name;
            }
        }

        return genreList;
    }

    public int getRuntime() {
        return this.movie.runtime;
    }

    public String getReleaseDate() {
        return new SimpleDateFormat("MMMM d, yyyy").format(this.movie.release_date);
    }

    public double getVoteAverage() {
        return this.movie.vote_average;
    }

    public int getVoteCount() {
        return this.movie.vote_count;
    }

    public String getIMDB_ID() {
        return this.movie.imdb_id;
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