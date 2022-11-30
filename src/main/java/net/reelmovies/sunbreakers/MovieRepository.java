package net.reelmovies.sunbreakers;

// import com.example.springtest.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;

// Interface used to store data to MongoDB
public interface MovieRepository extends MongoRepository<Movie, String> 
{
    
}