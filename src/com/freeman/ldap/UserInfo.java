package com.freeman.ldap;

public class UserInfo {

	private String mail;
	private String sap;
	
	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getSap() {
		return sap;
	}

	public void setSap(String sap) {
		this.sap = sap;
	}

	public String toString() {
		
		StringBuffer userInfoStringBuffer = new StringBuffer("Person=[");
		userInfoStringBuffer.append(" mail = " + mail);
		userInfoStringBuffer.append(", sap = " + sap);
		userInfoStringBuffer.append(" ]");
		return userInfoStringBuffer.toString();
	}
	
}
