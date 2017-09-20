package com.freeman.java.pattern;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexGrouping {

	private static final String REGEX = "(canon.ca|canon.com|custhelp.com|canon.info|customersupport|/InetDD/|/mps/)";
	private static final String INPUT = "www.canon.ca, canon.com, custhelp.com canon.info /InetDD/ asas /mps/";
	public static void main(String[] args) {
		Pattern p1 = Pattern.compile(REGEX);
		//  get a matcher object
		printMacher(p1.matcher(INPUT));
		
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
