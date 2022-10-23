package com.example.springtest;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "parseMovies")
public class Movie {
    @Id
    private String id;
    private String posterPath;
    private String title;
    private String overview;
    private String imdb_id;

    public Movie(String posterPath, String title, String overview, String imdb_id) {
        this.posterPath =  posterPath;
        this.title = title;
        this.overview = overview;
        this.imdb_id = imdb_id;
    }
}
