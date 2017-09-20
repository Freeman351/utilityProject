package com.freeman.j2ee.service.test;

import java.util.List;

public class CategoryCCITest extends BaseServiceTestCase {
	
	public void testGetCategoriesExist(){
		long ts = System.currentTimeMillis();
		List records = super.getbService().getCategory();
		ts = System.currentTimeMillis() - ts;
		System.out.println("testGetCategoriesExist() Done in " + ts + "ms.");

		assertFalse("Records object is null", records.isEmpty());
		System.out.println("Records number is " + records.size()); 
	}

}
