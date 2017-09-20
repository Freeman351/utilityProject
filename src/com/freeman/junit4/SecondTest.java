/**
 * 
 */
package com.freeman.junit4;


import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Q04706
 *
 */
public class SecondTest {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("In SecondTest @BeforeClass Method");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("In SecondTest @AfterClass Method");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		System.out.println("In @Before(setUp()) Method");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		System.out.println("In @After(tearDown) Method");
	}
	
//	@Test
	public void firstMethod(){
		
		System.out.println("In First Test Method");
		
	}

	@Test
	public void secondMethod(){
		
		System.out.println("In second Test Method");
		
	}
}
