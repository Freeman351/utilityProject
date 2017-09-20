package com.freeman.canada.post.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;


@XmlAccessorType(XmlAccessType.FIELD)
public class Notifications {
	
	@XmlElement(name="notification")	
	private List<Notification> notificationList;

	public List<Notification> getNotificationList() {
		return notificationList;
	}

	public void setNotificationList(List<Notification> notificationList) {
		this.notificationList = notificationList;
	}

	
}

//<notification>
//	<email>hong_li@canon.canada.ca</email>
//	on-shipment>false</on-shipment>
//	<on-exception>true</on-exception>
//	<on-delivery>true</on-delivery>
//</notification>
//<notification>
//	<email>henry_liu@canon.canada.ca</email>
//	on-shipment>false</on-shipment>
//	<on-exception>true</on-exception>
//	<on-delivery>true</on-delivery>
//</notification>
