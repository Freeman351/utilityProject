package com.freeman.java.pattern;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MatchesLookingDemo {
    private static final String REGEX = "foo";

    private static final String INPUT = "foo foo";
    
    public static void main(String[] args) {
   
        // Initialize
    	Pattern p1 = Pattern.compile(REGEX);
    	Matcher m1 = p1.matcher(INPUT);

        System.out.println("Current REGEX is: "  + REGEX);
        System.out.println("Current INPUT is: " + INPUT);

        System.out.println("lookingAt(): " + m1.lookingAt());
        System.out.println("matches(): " + m1.matches());
    }
}
