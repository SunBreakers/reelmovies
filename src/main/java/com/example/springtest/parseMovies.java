package com.example.springtest;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

import com.uwetrottmann.tmdb2.Tmdb;
import com.uwetrottmann.tmdb2.entities.Movie;
import com.uwetrottmann.tmdb2.entities.Genre;
import com.uwetrottmann.tmdb2.services.MoviesService;

import java.util.Random;
import java.io.IOException;
import java.text.SimpleDateFormat;

@Controller
public class parseMovies{
    private Movie movie = new Movie();
    private static String API_KEY;
    static int totalMoviesTested = 0;
    // private String movieRecommendations;
    // private String movieVideo;

    // Hide API Key by getting it from application.properties
    @Value("${TMDB_API_Key}")
    public void setMyProperty(String myValue) {
        this.API_KEY = myValue;
    }

    // https://www.themoviedb.org/documentation/api
    // https://github.com/UweTrottmann/tmdb-java
    public void getMoviesFromAPI(int movieID)
    {
        Random random = new Random();
        int randomMovie = movieID;

        Tmdb tmdb = new Tmdb(API_KEY);
        MoviesService moviesService = tmdb.moviesService(); 
        try 
        {
            retrofit2.Response<Movie> response = moviesService
                .summary(randomMovie, "") // 550 = Fight Club
                .execute();
            while(!response.isSuccessful()) {
                System.out.println("Response from API not successful trying again");
                randomMovie = random.nextInt(812104) + 1;
                response = moviesService
                    .summary(randomMovie, "")
                    .execute();
            }
            totalMoviesTested++;
            if (response.isSuccessful()) 
            {
                this.movie = response.body();
                System.out.println("Testing movie: (" + randomMovie + ") " + this.movie.title);
                // this.movie.popularity < 1
                while(this.movie == null || this.movie.adult == true || this.movie.poster_path == null || this.movie.imdb_id == null || this.movie.runtime == null || this.movie.release_date == null || this.movie.genres == null || !this.movie.original_language.contains("en")) // prevents null, adult movies or movies without posters from showing
                {
                    randomMovie = random.nextInt(812104) + 1;
                    response = moviesService
                        .summary(randomMovie, "")
                        .execute();
                    totalMoviesTested++;
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

    public int getID() {
        if(this.movie.id == null) {
            return 0;
        }
        return this.movie.id;
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
        if(this.movie.genres == null) {
            return null;
        }
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

    public String getRuntime() {
        if(this.movie.runtime == null) {
            return "";
        }
        int hours = this.movie.runtime / 60;
        int minutes = this.movie.runtime % 60;
        StringBuilder runtimeStringBuilder = new StringBuilder();
        if(hours > 0)
            runtimeStringBuilder.append(hours);
        if(hours > 1)
        {
            runtimeStringBuilder.append(" hours");
            runtimeStringBuilder.append(" and ");
        }
        else if(hours == 1)
        {
            runtimeStringBuilder.append(" hour");
            runtimeStringBuilder.append(" and ");
        }
        if(minutes > 0)
            runtimeStringBuilder.append(minutes);
        if(minutes > 1)
            runtimeStringBuilder.append(" minutes");
        else if(minutes == 1)
            runtimeStringBuilder.append(" minute");
        return runtimeStringBuilder.toString();
    }

    public String getReleaseDate() {
        if(this.movie.release_date == null) {
            return "null";
        }
        return new SimpleDateFormat("MMMM d, yyyy").format(this.movie.release_date);
    }

    public double getVoteAverage() {
        if(this.movie.vote_average == null) {
            return 0;
        }
        return this.movie.vote_average;
    }

    public int getVoteCount() {
        if(this.movie.vote_count == null) {
            return 0;
        }
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