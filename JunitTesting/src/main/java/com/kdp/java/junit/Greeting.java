package com.kdp.java.junit;

public class Greeting {

	public String greet(String str) {
		if (str == null || str.length() ==0){
			throw new IllegalArgumentException();
		}
		return "Hello " + str;
	}
}
