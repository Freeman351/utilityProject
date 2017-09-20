package com.freeman.java.pattern;

import java.util.regex.Pattern;

public class SplitDemo {

    private static final String REGEX1 = ":";
    private static final String INPUT1 = "one:two:three:four:five";
    private static final String REGEX2 = "\\d";
    private static final String INPUT2 = "one9two4three7four1five";
    
    public static void main(String[] args) {
        Pattern pattern1 = Pattern.compile(REGEX1);
        String[] items1 = pattern1.split(INPUT1);
        System.out.println("Items 1==================");
        for(String s : items1) {
            System.out.println(s);
        }
        Pattern pattern2 = Pattern.compile(REGEX2);
        String[] items2 = pattern2.split(INPUT2);
        System.out.println("Items 2==================");
        for(String s : items2) {
            System.out.println(s);
        }
    }
}