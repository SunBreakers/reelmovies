package net.reelmovies.sunbreakers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

@Controller
public class ApiGetMethod 
{
    static String API_KEY;

    // Hide API Key by getting it from application.properties
    @Value("${TMDB_API_Key}")
    public void setMyProperty(String myValue) 
    {
        this.API_KEY = myValue;
    }

    public static int getMovieFromDiscover()
    {
        String getHtmlString = getHTML(getDiscoverURLToRead());
        List<Integer> listOfMovieIDs = getMovieID(getHtmlString);
        int getRandomMovieIDFromDiscover = getRandomMovieIDFromDiscover(listOfMovieIDs);
        int randomMovieFromDiscover = getRandomMovieIDFromDiscover;
        return randomMovieFromDiscover;
    }

    public static String getDiscoverURLToRead() //Discover method
    {
        Random rand = new Random();
        StringBuilder urlStringBuilder = new StringBuilder();
        urlStringBuilder.append("https://api.themoviedb.org/3/discover/movie?api_key=");
        urlStringBuilder.append(API_KEY);
        urlStringBuilder.append("&language=en&sort_by=popularity.desc&include_adult=false&include_video=false");
        urlStringBuilder.append("&page=");
        urlStringBuilder.append(rand.nextInt(9)+1);
        urlStringBuilder.append("&with_watch_monetization_types=flatrate");
        return urlStringBuilder.toString();
    }

    public static String getPopularURLToRead() //Popular method
    {
        Random rand = new Random();
        StringBuilder urlStringBuilder = new StringBuilder();
        urlStringBuilder.append("https://api.themoviedb.org/3/movie/popular?api_key=");
        urlStringBuilder.append(API_KEY);
        urlStringBuilder.append("&language=en-US&page=");
        urlStringBuilder.append(rand.nextInt(9)+1);
        return urlStringBuilder.toString();
    }


    public static String getRecommendationsURLToRead(int movieID) //Recommendations method
    {
        StringBuilder urlStringBuilder = new StringBuilder();
        urlStringBuilder.append("https://api.themoviedb.org/3/movie/");
        urlStringBuilder.append(String.valueOf(movieID));
        urlStringBuilder.append("/recommendations?api_key=");
        urlStringBuilder.append(API_KEY);
        urlStringBuilder.append("&language=en-US&page=1");
        return urlStringBuilder.toString();
    }

    public static String getHTML(String urlToRead)
    {
        try
        {
            StringBuilder result = new StringBuilder();
            URL url = new URL(urlToRead);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()))) 
            {
                for (String line; (line = reader.readLine()) != null; ) 
                {
                    result.append(line);
                }
            }
            // System.out.println("index of id" + result.indexOf("\"id\":") + "\n");
            return result.toString();
        } 
        catch(Exception e) 
        {
            e.printStackTrace();
            return "";
        }
    }

    public static List<Integer> getMovieID(String stringFromGetHtml) 
    {
        try
        {
            List<Integer> listOfMovieIDs = new ArrayList<Integer>();
            String movieIDAsString = "";
    
            for(int i = 0; i < stringFromGetHtml.length()-5; i++) 
            {
                movieIDAsString = "";
                String testForID = stringFromGetHtml.substring(i, i+5);
                // Checks if substring equals "id\":
                if(testForID.equals("\"id\":")) 
                {
                    for(int j = 0; j < 6; j++) 
                    {
                        // Checks if ASCII value of char after substring is an int
                        if((int)stringFromGetHtml.charAt(i+5+j) >= 48 && (int)stringFromGetHtml.charAt(i+5+j) <= 57) 
                        {
                            movieIDAsString += stringFromGetHtml.charAt(i+5+j);
                            // System.out.println(movieIDAsString);
                        }
                    }
                    // Converts string to int and then adds to the List
                    int stringToInt = Integer.parseInt(movieIDAsString);
                    listOfMovieIDs.add(stringToInt);
                    // System.out.println(listOfMovieIDs);
                }
            }
            return listOfMovieIDs;
        } 
        catch(Exception e) 
        {
            e.printStackTrace();
            return null;
        }
    }

    public static int getRandomMovieIDFromDiscover(List<Integer> listOfMovieIDs)
    {
        Random rand = new Random();
        int randomMovieIDFromDiscover = listOfMovieIDs.get(rand.nextInt(listOfMovieIDs.size()));
        return randomMovieIDFromDiscover;
    }
}
