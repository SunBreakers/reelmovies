package com.example.springtest;

import org.junit.*;

public class GenerateRandomIntTest {
    private GenerateRandomInt c;

    @Before
    public void setup() {
        System.out.println("before testing ...");
        c = new GenerateRandomInt();
    }

    @After
    public void cleanup() {
        System.out.println("Finishing and cleaning up the test ...");
    }

    // Julian Rowe
    @Test
    public void testGenerateRandInt() {
        int randIntTest = c.generateRandInt();
        Assert.assertTrue(0 <= randIntTest && randIntTest < 10);
    }
}