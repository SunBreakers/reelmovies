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
        parseMovies m = new parseMovies();
        m.setMovie(550);
        String testOverview = m.getOverview();
        Assert.assertEquals("A ticking-time-bomb insomniac and a slippery soap salesman channel primal male aggression into a shocking new form of therapy. Their concept catches on, with underground \"fight clubs\" forming in every town, until an eccentric gets in the way and ignites an out-of-control spiral toward oblivion.", testOverview);
    }

    //Noe Rivera
    @Test
    public void testGetRunTime(){
        m.setMovie(550);
        int testRunTime = m.getRuntime();
        Assert.assertEquals(139, testRunTime);
    }
}