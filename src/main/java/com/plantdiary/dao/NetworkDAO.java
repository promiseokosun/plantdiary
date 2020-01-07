package com.plantdiary.dao;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.stereotype.Component;

@Component
public class NetworkDAO {
	
	private HttpURLConnection urlConnection;

	/**
	 * Get the value from a given endpoint
	 * @param endpoint
	 * @return the full data obtained from the endpoint
	 * @throws Exception
	 */
	public String request(String endpoint) throws Exception{
		StringBuilder sb = new StringBuilder();
		URL url = new URL(endpoint);
		
		try{
			
			urlConnection = (HttpURLConnection) url.openConnection();
			
			// read the bytes in 1's and 0's
			InputStream inputStream = urlConnection.getInputStream();
			BufferedInputStream bis = new BufferedInputStream(inputStream); // save the bytes in buffer in memory
			
			// convert bytes to characters
			InputStreamReader inputStreamReader = new InputStreamReader(bis);
			BufferedReader reader = new BufferedReader(inputStreamReader);
			
			String data = reader.readLine();
			while( data != null){
				sb.append(data);
				data = reader.readLine();
			}
			
		} finally{
			// closing operation
			urlConnection.disconnect();
		}

		return sb.toString();
	}

}
