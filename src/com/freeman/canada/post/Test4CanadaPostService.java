package com.freeman.canada.post;

import java.io.FileOutputStream;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.freeman.canada.post.service.CanadaPostService;


public class Test4CanadaPostService {

	public static final String BEAN_XML_FILE = "beanContext4Test.xml";
	private static final String LANGUAGE_ENGLISH = "en";
	private static final String LANGUAGE_FRENCH = "fr";
	
	public static void main(String[] args) throws Exception {
		CanadaPostService cpService = (CanadaPostService) new Test4CanadaPostService() 
				.getApplicationContext(BEAN_XML_FILE).getBean("canadaPostService");
		String response = new String();

//		response = cpService.getAuthorizedReturn(LANGUAGE_ENGLISH, TestData4CanadaPost.getAuthorizedReturnRequest());
//		System.out.println("Open Authorized Return info==" + response);
//
//		byte[] artifactArray = cpService.getArtifact(LANGUAGE_ENGLISH, "https://ct.soa-gw.canadapost.ca/ers/artifact/2a22941da5465b37/343106/0");
//		System.out.println("Return Artifact info length==" + artifactArray.length);

		byte[] artifactArray = cpService.geAuthorizedReturnDocument(LANGUAGE_ENGLISH, TestData4CanadaPost.getAuthorizedReturnRequestString());
		System.out.println("Return Artifact info length==" + artifactArray.length);
		write2file("AuthorizedReturn.pdf",  artifactArray);
		
		byte[] artifactArray1 = cpService.geAuthorizedReturnDocument(LANGUAGE_ENGLISH, TestData4CanadaPost.getAuthorizedReturnRequest());
		System.out.println("Return Artifact1 info length==" + artifactArray1.length);
		write2file("AuthorizedReturn1.pdf",  artifactArray1);

	}
	
	private ApplicationContext getApplicationContext(String beanXmlFileName){
		return  new ClassPathXmlApplicationContext(beanXmlFileName);
	}
	
	private static void write2file(String fileName, byte[] fileContent) throws Exception {
		FileOutputStream fos = new FileOutputStream(fileName);
		fos.write(fileContent);
		fos.close();		
	}
}
