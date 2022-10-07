package com.example.springtest;

import clojure.java.api.Clojure;
import clojure.lang.IFn;

public class clojureTest 
{
    static String test;
    public static String clojure(){
        StringBuilder str = new StringBuilder();
         
        IFn plus = Clojure.var("clojure.core", "+");
        str.append("Hello World.");

        test = str.toString();
        return test;
    }
}
