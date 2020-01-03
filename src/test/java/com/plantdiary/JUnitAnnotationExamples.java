package com.plantdiary;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class JUnitAnnotationExamples {
	
	@BeforeClass
	public static void setupEverything(){
		int i = 1 + 1;
		assertEquals(2, i);
	}

	@Before
	public void setupBeforeEachTask(){
		int i = 1 + 1;
	}

	@After
	public void teardownAfterEachTask(){
		int i = 1 + 1;
	}

	@AfterClass
	public static void teardownEverything(){
		int i = 1 + 1;
	}

	@Test
	public void runTest(){
		int i = 1 + 1;
		assertEquals(2, i);
	}
	
	@Test
	public void runMoreTest(){
		double i = 1.2 + 1;
		assertEquals(2, i, 0.3); // delta is the clearance i.e the actual should not deviate from the expected more than 0.3
	}

}
