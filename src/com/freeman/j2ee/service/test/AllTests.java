package com.freeman.j2ee.service.test;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite(AllTests.class.getName());
		//$JUnit-BEGIN$
		suite.addTestSuite(CategoryCCITest.class);//MTLK168
		//$JUnit-END$
		return suite;
	}

}
