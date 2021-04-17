package com.finx.util;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class JsonParse {

	// http://localhost:8080/RESTfulExample/json/product/post
	public static void main(String[] args) {

	  try {

		URL url = new URL("http://10.210.48.197/api/pipeline");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setDoOutput(true);
		System.out.println("connection true");
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "application/json");	
		System.out.println("content type set");

		String input = "{\"Type\":Pipeline,\"PageSize\":20,\"SortFieldInformation\":{Loan.LoanNumber:\"ASC\"},\"PageIndex\":1,\"FilterCriteria\":{\"FieldId\":Loan.LoanNumber,\"FieldValue\":700170302,\"FieldMatchType\":contains,\"FieldDataType\":string},"
				+ "RequiredFields\":[Loan.LoanNumber,\"Loan.Guid\",\"749\",\"3142\",\"CX.BROKERLOANSTATUS\"]}";
        
    		
		OutputStream os = conn.getOutputStream();
		os.write(input.getBytes());
		os.flush();

		if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
			throw new RuntimeException("Failed : HTTP error code : "
				+ conn.getResponseCode());
		}

		BufferedReader br = new BufferedReader(new InputStreamReader(
				(conn.getInputStream())));

		String output;
		System.out.println("Output from Server .... \n");
		while ((output = br.readLine()) != null) {
			System.out.println(output);
		}

		conn.disconnect();

	  } catch (MalformedURLException e) {

		e.printStackTrace();

	  } catch (IOException e) {

		e.printStackTrace();

	 }
	}
}

	

