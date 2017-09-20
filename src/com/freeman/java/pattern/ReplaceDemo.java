package com.freeman.java.pattern;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReplaceDemo {
 
    private static String REGEX1 = "(dog|say)";
    private static String INPUT1 = "The DOG says meow. All dogs SAY meow.";
    private static String REPLACE1 = "cat";
    
    private static String REGEX2 = "a*b";
    private static String REGEX3 = "a+b";
    private static String REGEX4 = "a?b";
    private static String INPUT2 = "abfooaabfooaaabfoob";
    private static String REPLACE2 = "-";
 
    public static void main(String[] args) {
        Pattern p1 = Pattern.compile(REGEX1, Pattern.CASE_INSENSITIVE);
        // get a matcher object
        Matcher m1 = p1.matcher(INPUT1);
        System.out.println(m1.replaceAll(REPLACE1));
        
        Pattern p2 = Pattern.compile(REGEX2);
        // get a matcher object
        Matcher m2 = p2.matcher(INPUT2);
        System.out.println(m2.replaceAll(REPLACE2));

        Pattern p3 = Pattern.compile(REGEX3);
        // get a matcher object
        Matcher m3 = p3.matcher(INPUT2);
        System.out.println(m3.replaceAll(REPLACE2));

        Pattern p4 = Pattern.compile(REGEX4);
        // get a matcher object
        Matcher m4 = p4.matcher(INPUT2);
        System.out.println(m4.replaceAll(REPLACE2));
    }
}