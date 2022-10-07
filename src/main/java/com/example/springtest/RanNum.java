package com.example.springtest;

import org.apache.commons.math3.distribution.NormalDistribution;

public class RanNum {
    

    public static double calculate() {
        NormalDistribution normalDistribution = new NormalDistribution(10, 3);
        double randomValue = normalDistribution.sample();
        return randomValue;
    }

}
