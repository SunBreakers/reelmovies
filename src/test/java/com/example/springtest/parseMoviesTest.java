package com.example.springtest;

import org.junit.*;

public class parseMoviesTest {
    private parseMovies m;

    @Before
    public void setup() {
        System.out.println("before testing ...");
        m = new parseMovies();
    }

    @After
    public void cleanup() {
        System.out.println("Finishing and cleaning up the test ...");
    }

    // Julian Rowe
    @Test
    public void testParseMovieTitle() {
        m.setMovie(550);
        String testTitle = m.getTitle();
        Assert.assertEquals("Fight Club", testTitle);
    }

    @Test
    public void testParseMovieOverview() {
        m.setMovie(550);
        String testOverview = m.getOverview();
        Assert.assertEquals("A ticking-time-bomb insomniac and a slippery soap salesman channel primal male aggression into a shocking new form of therapy. Their concept catches on, with underground \"fight clubs\" forming in every town, until an eccentric gets in the way and ignites an out-of-control spiral toward oblivion.", testOverview);
    }
    //Duc Thanh Nguyen
    @Test
    public void testIMDB_ID(){
        parseMovies m = new parseMovies();
        m.setMovie(550);
        String testIMDB = m.getIMDB_ID();
        Assert.assertNotNull(testIMDB);
    }

    //Noe Rivera
    @Test
    public void testGetRunTime(){
        m.setMovie(550);
        int testRunTime = m.getRuntime();
        Assert.assertEquals(139, testRunTime);
    }

    // Tyler Kloss
    @Test
    public void testParseMoviePoster() {
        m = new parseMovies();
        m.setMovie(550);
        String testPoster = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2" + m.getPosterPath();
        Assert.assertEquals("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/pB8BM7pdSp6B6Ih7QZ4DrQ3PmJK.jpg", testPoster);
    }
    //Ryan Mercado
    @Test
    public void testParseReleaseDate(){
        m.setMovie(550);
        String testDate = m.getReleaseDate();
        Assert.assertEquals("October 15, 1999",testDate);
    }
}
