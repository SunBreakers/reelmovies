package com.example.reelmovies;

// import com.example.springtest.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MovieRepository extends MongoRepository<Movie, String> 
{
    
}