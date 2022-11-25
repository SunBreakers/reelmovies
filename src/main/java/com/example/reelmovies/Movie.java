package com.example.reelmovies;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

// import java.util.List;
// import com.uwetrottmann.tmdb2.entities.Genre;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Document(collection = "parseMovies")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Movie {
    @Id
    private String id;
    private String posterPath;
    private String title;
    private String overview;
    private String genres;
    private String runtime;
    private String releaseDate;
    private double voteAverage;
    private int voteCount;
    private String imdb_id;

    public Movie(String posterPath, String title, String overview, String genres, String runtime, String releaseDate, double voteAverage, int voteCount, String imdb_id) {
        this.posterPath =  posterPath;
        this.title = title;
        this.overview = overview;
        this.genres = genres;
        this.runtime = runtime;
        this.releaseDate = releaseDate;
        this.voteAverage = voteAverage;
        this.voteCount = voteCount;
        this.imdb_id = imdb_id;
    }
}
