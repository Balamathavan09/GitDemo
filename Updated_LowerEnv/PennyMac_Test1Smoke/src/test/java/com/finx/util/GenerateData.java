package com.finx.util;

import java.io.IOException;

import jxl.read.biff.BiffException;

import org.apache.commons.lang.RandomStringUtils;

import com.tavant.utils.TwfException;

public class GenerateData {

	/**
	 * Method Name:generateRandomString
	 * Purpose: Generate random string
	 * 
	 * @param interger
	 * 
	 */

	
	public static String generateRandomString(int length) {
		return RandomStringUtils.randomAlphabetic(length);
	}
	
	/**
	 * Method Name: generateRandomNumber
	 * Purpose: Generate random number
	 * 
	 * @param interger
	 * 
	 */

	public static String generateRandomNumber(int length) {
		return RandomStringUtils.randomNumeric(length);
	}
	/**
	 * Method Name: generateRandomAlphaNumeric
	 * Purpose: Generate random alphanumeric
	 * 
	 * @param length
	 * 
	 */
	public static String generateRandomAlphaNumeric(int length) {
		return RandomStringUtils.randomAlphanumeric(length);
	}
	
	/**
	 * Method Name: generateStringWithAllobedSplChars
	 * Purpose: Generate string with special character
	 *   
	 * @param interger and allowed special character
	 * 
	 */

	public static String generateStringWithAllobedSplChars(int length, String allowdSplChrs) {
		String allowedChars = "abcdefghijklmnopqrstuvwxyz" + // alphabets
				"1234567890" + // numbers
				allowdSplChrs;
		return RandomStringUtils.random(length, allowedChars);
	}
	
	/**
	 * Method Name: generateEmail
	 * Purpose: Generate the email
	 *   
	 * @param length
	 * 
	 */

	public static String generateEmail(int length) {
		String allowedChars = "abcdefghijklmnopqrstuvwxyz" + // alphabets
				"1234567890" + // numbers
				"_-."; // special characters
		String email = "";
		String temp = RandomStringUtils.random(length, allowedChars);
		System.out.println("Temp>>>>"+temp);
		email = temp.substring(0, temp.length()) + "@finbroker.com";
		return email;
	}
	
	/**
	 * Method Name: generateUrl
	 * Purpose: Generate the url
	 *   
	 * @param length
	 * 
	 */

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
	
	/**
	 * Method Name: generateFirstName
	 * Purpose: Generate the first name
	 *   
	 * @param length
	 * 
	 */

	public static String generateFirstName(int length) {
		String allowedChars = "abcdefghijklmnopqrstuvwxyz" + // alphabets
				"1234567890";
		String firstname = "";
		String temp = RandomStringUtils.random(length, allowedChars);
		firstname = "firstname" + temp.substring(0, temp.length() - 9);
		return firstname;
	}
	
	/**
	 * Method Name: generateLastName
	 * Purpose: Generate the last name
	 *   
	 * @param length
	 * 
	 */


	public static String generateLastName(int length) {
		String allowedChars = "abcdefghijklmnopqrstuvwxyz" + // alphabets
				"1234567890";
		String lastname = "";
		String temp = RandomStringUtils.random(length, allowedChars);
		lastname = "lastname" + temp.substring(0, temp.length() - 9);
		return lastname;
	}


	
    
	

}
