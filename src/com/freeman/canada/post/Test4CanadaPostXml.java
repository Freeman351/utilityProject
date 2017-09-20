package com.freeman.canada.post;

import com.freeman.canada.post.model.AuthorizedReturnRequest;
import com.freeman.utilities.JaxbXmlUtil;


public class Test4CanadaPostXml {

	public static void main(String[] args) throws Exception {
		
		String testXmlString;
		testXmlString = TestData4CanadaPost.getAuthorizedReturnRequestString();
		System.out.println("unmarshal String ==");
		System.out.println(testXmlString );
		AuthorizedReturnRequest info =  JaxbXmlUtil.parseXmlString(testXmlString, AuthorizedReturnRequest.class);
		System.out.println("unmarshal done" );
		System.out.println("" );
		
		testXmlString = JaxbXmlUtil.generateXmlString(info);
		System.out.println("Marshalled xml ==" );
		System.out.println(testXmlString);
	}
	
}
