package com.freeman.canada.post;

import java.util.ArrayList;
import java.util.List;

import com.freeman.canada.post.model.AuthorizedReturnRequest;
import com.freeman.canada.post.model.DomesticAddress;
import com.freeman.canada.post.model.Notification;
import com.freeman.canada.post.model.Notifications;
import com.freeman.canada.post.model.ParcelCharacteristics;
import com.freeman.canada.post.model.PrintPreferences;
import com.freeman.canada.post.model.Receiver;
import com.freeman.canada.post.model.Returner;

public class TestData4CanadaPost {

	static public String getAuthorizedReturnRequestString(){
		StringBuffer infoBuffer = new StringBuffer("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
		infoBuffer.append("<authorized-return xmlns=\"http://www.canadapost.ca/ws/authreturn-v2\">");
		infoBuffer.append("<service-code>DOM.RP</service-code>");
		infoBuffer.append("<returner>");
		infoBuffer.append("<name>Jane Doe</name>");
		infoBuffer.append("<company>Capsule Corp.</company>");
		infoBuffer.append("<domestic-address>");
		infoBuffer.append("<address-line-1>2701 Return Avenue</address-line-1>");
		infoBuffer.append("<city>Ottawa</city>");
		infoBuffer.append("<province>ON</province>");
		infoBuffer.append("<postal-code>K1A0B1</postal-code>");
		infoBuffer.append("</domestic-address>");
		infoBuffer.append("</returner>");
		infoBuffer.append("<receiver>");
		infoBuffer.append("<name>Andrea Chmielinski</name>");
		infoBuffer.append("<company>Canon canada Inc.</company>");
		infoBuffer.append("<domestic-address>");
		infoBuffer.append("<address-line-1>6390 Dixie Rd</address-line-1>");
		infoBuffer.append("<city>Mississauga</city>");
		infoBuffer.append("<province>ON</province>");
		infoBuffer.append("<postal-code>L5T1P7</postal-code>");
		infoBuffer.append("</domestic-address>");
		infoBuffer.append("</receiver>");
		infoBuffer.append(" <parcel-characteristics><weight>15</weight></parcel-characteristics>");
		infoBuffer.append("<print-preferences><output-format>8.5x11</output-format></print-preferences>");
		infoBuffer.append("<settlement-info></settlement-info>");
		infoBuffer.append("<notifications>");
		infoBuffer.append("<notification>");
		infoBuffer.append("<email>hong_li@canon.canada.ca</email>");
		infoBuffer.append("<on-shipment>false</on-shipment>");
		infoBuffer.append("<on-exception>true</on-exception>");
		infoBuffer.append("<on-delivery>true</on-delivery>");
		infoBuffer.append("</notification>");
		infoBuffer.append("</notifications>");		
		infoBuffer.append("</authorized-return >");
		return infoBuffer.toString();
	}

	static public String getAuthorizedReturnInfo(){
		StringBuffer infoBuffer = new StringBuffer("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
		infoBuffer.append("<authorized-return-info xmlns=\"http://www.canadapost.ca/ws/authreturn-v2\">");
		infoBuffer.append("<tracking-pin>123456789012</tracking-pin>");
		infoBuffer.append("<links>");
		infoBuffer.append("<link href=\"https://ct.soa-gw.canadapost.ca/ers/artifact/2a22941da5465b37/333594/0\" rel=\"returnLabel\" media-type=\"application/pdf\" index=\"0\">123</link>");
		infoBuffer.append("</links>");
		infoBuffer.append("</authorized-return-info>");
		return infoBuffer.toString();
	}
	
	static public String getOpenReturnRequest(String contractIdInCanadaPost){
		StringBuffer infoBuffer = new StringBuffer("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
		infoBuffer.append("<open-return xmlns=\"http://www.canadapost.ca/ws/openreturn-v2\">");
		infoBuffer.append("<max-number-of-artifacts>10</max-number-of-artifacts>");
		infoBuffer.append("<service-code>DOM.EP</service-code>");
		infoBuffer.append("<receiver><domestic-address>");
		infoBuffer.append("<address-line-1>2701 Riverside Drive</address-line-1>");
		infoBuffer.append("<city>Ottawa</city>");
		infoBuffer.append("<province>ON</province>");
		infoBuffer.append("<postal-code>K1A0B1</postal-code>");
		infoBuffer.append("</domestic-address></receiver>");
		infoBuffer.append("<print-preferences><output-format>8.5x11</output-format></print-preferences>");
		infoBuffer.append("<settlement-info><contract-id>" + contractIdInCanadaPost + "</contract-id></settlement-info>");
		infoBuffer.append("</open-return>");
		return infoBuffer.toString();
	}
	
	static public String getAuthorizedReturnLinkInfo(){
		StringBuffer infoBuffer = new StringBuffer("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
		infoBuffer.append("<link href=\"https://ct.soa-gw.canadapost.ca/ers/artifact/2a22941da5465b37/333594/0\" rel=\"returnLabel\" media-type=\"application/pdf\" index=\"0\">123</link>");
		return infoBuffer.toString();
	}
	
	static public AuthorizedReturnRequest getAuthorizedReturnRequest(){
		AuthorizedReturnRequest request = new AuthorizedReturnRequest();
		request.setServiceCode("DOM.RP");
		request.setSettlement("");
		
		Returner returner = new Returner();
		returner.setName("Jane Doe");
		returner.setCompany("Capsule Corp.");
		DomesticAddress returnerAddress = new DomesticAddress();
		returnerAddress.setProvince("ON");
		returnerAddress.setCity("Ottawa");
		returnerAddress.setPostalCode("K1A0B1");
		returnerAddress.setStreet("2701 Return Avenue");
		returner.setAddress(returnerAddress);
		
		request.setReturner(returner);
		
		Receiver receiver = new Receiver();
		receiver.setName("Andrea Chmielinski");
		receiver.setCompany("Canon canada Inc.");
		DomesticAddress receiverAddress = new DomesticAddress();
		receiverAddress.setProvince("ON");
		receiverAddress.setCity("Mississauga");
		receiverAddress.setPostalCode("L5T1P7");
		receiverAddress.setStreet("6390 Dixie Rd");
		receiver.setAddress(receiverAddress);
		
		request.setReceiver(receiver);
		
		ParcelCharacteristics parcelCharacteristics = new ParcelCharacteristics();
		parcelCharacteristics.setWeight("15");
		
		request.setParcelCharacteristics(parcelCharacteristics);
		
		PrintPreferences printPreferences = new PrintPreferences();
		printPreferences.setOutputFormat("8.5x11");
		
		request.setPrintPreferences(printPreferences);
		
		Notification notification = new Notification();
		notification.setEmail("hong_li@canon.canada.ca");
		notification.setOnException(true);
		notification.setOnDelivery(true);
		
		List<Notification> notificationList = new ArrayList<Notification>();
		notificationList.add(notification);
		
		Notifications notifications = new Notifications();
		notifications.setNotificationList(notificationList);
				
		request.setNotifications(notifications);
		
		return request;		
	}
}
