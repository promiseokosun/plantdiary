package com.plantdiary.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestDB {

	public static void main(String[] args) {
		
		String url = "jdbc:mysql://localhost:3306/plants";
		String username = "promiseland";
		String password = "4real";
		
		try{
			Connection con = DriverManager.getConnection(url, username, password );
			System.out.println("Database Connection is Successful!");
			
		} catch(Exception e){
			e.printStackTrace();
		}
		

	}

}
