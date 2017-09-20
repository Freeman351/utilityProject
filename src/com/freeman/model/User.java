package com.freeman.model;

import java.util.Date;


public class User extends BaseEntity {

	private String userName;
	private String password;
	private String email;
	private String firstName;
	private String lastName;
	private Date createdDate;
	private String createdIp;
	private Date updatedDate;

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedIp() {
		return this.createdIp;
	}

	public void setCreatedIp(String createdIp) {
		this.createdIp = createdIp;
	}

	public Date getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	/*
	 * private Set<Bookmark> bookmarks; private Set<Label> labels; public
	 * Set<Bookmark> getBookmarks() { return bookmarks; }
	 * 
	 * public void setBookmarks(Set<Bookmark> bookmarks) { this.bookmarks =
	 * bookmarks; }
	 * 
	 * public void setLabels(Set<Label> labels) { this.labels = labels; }
	 * 
	 * public Set<Label> getLabels() { return labels; }
	 */

}
