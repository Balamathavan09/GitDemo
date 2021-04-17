package com.finx.util;

import org.apache.commons.lang.RandomStringUtils;

public class GenerateData2 {

	public static String generateRandomString(int length) {
		return RandomStringUtils.randomAlphabetic(length);
	}

	public static String generateRandomNumber(int length) {
		return RandomStringUtils.randomNumeric(length);
	}

	public static String generateRandomAlphaNumeric(int length) {
		return RandomStringUtils.randomAlphanumeric(length);
	}

	public static String generateStringWithAllobedSplChars(int length, String allowdSplChrs) {
		String allowedChars = "abcdefghijklmnopqrstuvwxyz" + // alphabets
				"1234567890" + // numbers
				allowdSplChrs;
		return RandomStringUtils.random(length, allowedChars);
	}

	public static String generateEmail(int length) {
		String allowedChars = "abcdefghijklmnopqrstuvwxyz" + // alphabets
				"1234567890" + // numbers
				"_-."; // special characters
		String email = "";
		String temp = RandomStringUtils.random(length, allowedChars);

		email = temp.substring(0, temp.length() - 9) + "@finbroker.com";
		return email;
	}

	public static String generateUrl(int length) {
		String allowedChars = "abcdefghijklmnopqrstuvwxyz" + // alphabets
				"1234567890" + // numbers
				"_-."; // special characters
		String url = "";
		String temp = RandomStringUtils.random(length, allowedChars);
		url = temp.substring(0, 3) + "." + temp.substring(4, temp.length() - 4) + "."
				+ temp.substring(temp.length() - 3);
		return url;
	}

	public static String generateFirstName(int length) {
		String allowedChars = "abcdefghijklmnopqrstuvwxyz" + // alphabets
				"1234567890";
		String firstname = "";
		String temp = RandomStringUtils.random(length, allowedChars);
		firstname = "firstname" + temp.substring(0, temp.length() - 9);
		return firstname;
	}

	public static String generateLastName(int length) {
		String allowedChars = "abcdefghijklmnopqrstuvwxyz" + // alphabets
				"1234567890";
		String lastname = "";
		String temp = RandomStringUtils.random(length, allowedChars);
		lastname = "lastname" + temp.substring(0, temp.length() - 9);
		return lastname;
	}


	
    
	

}
