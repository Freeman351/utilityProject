package com.freeman.ldap.test;

import java.util.LinkedList;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.freeman.ldap.service.LdapService;

public class Test4LdapService {

	public static final String BEAN_XML_FILE = "/com/freeman/ldap/test/beanContext4Ldap.xml";
	public static final String BEAN_NAME = "ldapService";
	
	public static void main(String[] args) throws Exception {
		LdapService ldapService = (LdapService) new Test4LdapService() .getApplicationContext(BEAN_XML_FILE).getBean(BEAN_NAME);
		
		LinkedList  userInfo =  (LinkedList) ldapService.getContactDetails("uid", "Q04706@CCI.CANON.INFO");
		System.out.println(userInfo.toString());
	}

	private ApplicationContext getApplicationContext(String beanXmlFileName){
		return  new ClassPathXmlApplicationContext(beanXmlFileName);
	}
	
}
