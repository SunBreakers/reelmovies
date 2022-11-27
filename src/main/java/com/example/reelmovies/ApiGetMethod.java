package com.example.reelmovies;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

import java.io.*;
import java.net.*;
import java.util.*;

@Controller
public class ApiGetMethod 
{
    static String API_KEY;

    // Hide API Key by getting it from application.properties
    @Value("${TMDB_API_Key}")
    public void setMyProperty(String myValue) {
        this.API_KEY = myValue;
    }

    public static String getURLToRead()
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
        } catch(Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static List<Integer> getMovieID(String getHtmlString) 
    {
        try
        {
            List<Integer> listOfMovieIDs = new ArrayList<Integer>();
            String movieIDAsString = "";
    
            for(int i = 0; i < getHtmlString.length()-5; i++) 
            {
                movieIDAsString = "";
                String testForID = getHtmlString.substring(i, i+5);
                // Checks if substring equals "id\":
                if(testForID.equals("\"id\":")) 
                {
                    for(int j = 0; j < 6; j++) 
                    {
                        // Checks if ASCII value of char after substring is an int
                        if((int)getHtmlString.charAt(i+5+j) >= 48 && (int)getHtmlString.charAt(i+5+j) <= 57) 
                        {
                            movieIDAsString += getHtmlString.charAt(i+5+j);
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
        } catch(Exception e) {
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
