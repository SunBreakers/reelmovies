package com.example.springtest;

import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import java.util.List;

public class PredicateEx {
    static String predicate;
    public static String getICE(){
        StringBuilder str = new StringBuilder();

        List<Integer> values = Lists.newArrayList(3, null, 4, 7, 
                8, null, 7);
        
        Iterable<Integer> filtered = Iterables.filter(values, 
                Predicates.notNull());
        
        for (Integer i: filtered) {
            //System.out.println(i);
            str.append("<br>value: " + i);
        }
        predicate = str.toString();
        return predicate;
    }
}
