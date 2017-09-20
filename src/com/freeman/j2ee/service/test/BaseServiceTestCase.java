package com.freeman.j2ee.service.test;

import junit.framework.TestCase;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

import com.freeman.j2ee.service.CCI21Service;

public class BaseServiceTestCase extends TestCase {
	private CCI21Service bService;

	protected void setUp(){
		ClassPathResource res = new ClassPathResource("testServiceContext.xml");
		XmlBeanFactory xmlBeanFactory = new XmlBeanFactory(res);
		bService = (CCI21Service) xmlBeanFactory.getBean("CCI21Service");		
	}
	
	public CCI21Service getbService() {
		return bService;
	}

}
