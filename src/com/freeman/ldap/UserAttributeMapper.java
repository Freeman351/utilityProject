package com.freeman.ldap;

import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;

import org.springframework.ldap.core.AttributesMapper;

public class UserAttributeMapper implements AttributesMapper {

	public Object mapFromAttributes(Attributes attributes) throws NamingException {
		UserInfo userInfo = new UserInfo();
		 
		Attribute mail = attributes.get("mail");
		Attribute sap = attributes.get("memberOf");
		if(mail != null)
			userInfo.setMail((String)mail.get());
		if(sap != null)
			userInfo.setSap((String)sap.get());
 
		return userInfo;	
	}

}
