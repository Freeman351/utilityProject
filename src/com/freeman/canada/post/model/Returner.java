package com.freeman.canada.post.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="returner")
public class Returner extends ContactPerson {

}

//infoBuffer.append("<returner>");
//infoBuffer.append("<name>Jane Doe</name>");
//infoBuffer.append("<company>Capsule Corp.</company>");
//infoBuffer.append("<domestic-address>");
//infoBuffer.append("<address-line-1>2701 Return Avenue</address-line-1>");
//infoBuffer.append("<city>Ottawa</city>");
//infoBuffer.append("<province>ON</province>");
//infoBuffer.append("<postal-code>K1A0B1</postal-code>");
//infoBuffer.append("</domestic-address>");
//infoBuffer.append("</returner>");
