package com.freeman.ldap.service;

import java.util.List;

import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.filter.AndFilter;
import org.springframework.ldap.filter.EqualsFilter;

import com.freeman.ldap.UserAttributeMapper;

public class LdapServiceImpl implements LdapService {

	private LdapTemplate ldapTemplate;
	
	public List getAllContactNames() {
		// TODO Auto-generated method stub
		return null;
	}

	public List getContactDetails(String searchKey, String searchValue) {
		AndFilter andFilter = new AndFilter();
		andFilter.and(new EqualsFilter(searchKey, searchValue));
//		andFilter.and(new EqualsFilter("ou", "Groups"));
//		andFilter.and(new EqualsFilter("memberUid", searchValue));
		System.out.println("LDAP Query " + andFilter.encode());
		return ldapTemplate.search("", andFilter.encode(),new UserAttributeMapper());
 	}

	public LdapTemplate getLdapTemplate() {
		return ldapTemplate;
	}

	public void setLdapTemplate(LdapTemplate ldapTemplate) {
		this.ldapTemplate = ldapTemplate;
	}

}
