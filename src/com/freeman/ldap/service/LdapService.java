package com.freeman.ldap.service;

import java.util.List;

public interface LdapService {
	
	public List getAllContactNames();
	public List getContactDetails(String searchKey, String searchValue);
	

}
