package com.freeman.java.pattern;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MatcherDemo {

	private static final String REGEX1 = "\\bdog\\b";
	private static final String REGEX2 = "dog";
	private static final String INPUT = "dog dog dog doggie dogg";

	public static void main(String[] args) {
		Pattern p1 = Pattern.compile(REGEX1);
		Pattern p2 = Pattern.compile(REGEX2);
		//  get a matcher object
		printMacher(p1.matcher(INPUT));
		printMacher(p2.matcher(INPUT));
		
	}
	
	private static void printMacher(Matcher m){
		System.out.println("Match object == "  +  m.toString());
		int count = 0;
		while(m.find()) {
			count++;
			System.out.println("Match number " + count + "==" + "start(): " + m.start() + "==" + "end(): " + m.end());
		}
	}

}