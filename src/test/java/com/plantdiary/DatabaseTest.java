package com.plantdiary;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import com.plantdiary.db.TestDB;

public class DatabaseTest {
	
	private TestDB testDB;

	@Before
	public void setupDB(){
		 testDB = new TestDB();
	}
	
	@Test
	public void databaseTest(){
		assertNotNull(testDB);
	}
	
	
	

}
