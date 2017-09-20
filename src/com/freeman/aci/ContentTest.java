package com.freeman.aci;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.freeman.aci.model.Content2;

public class ContentTest {

	private static final String DATA_FILE = "./AutnResponse.xml";

	public static void main(String[] args) throws JAXBException, IOException {


	    // get variables from our xml file, created before
	    JAXBContext context = JAXBContext.newInstance(Content2.class);
	    Unmarshaller um = context.createUnmarshaller();
	    Content2 autnresponse = (Content2) um.unmarshal(new ByteArrayInputStream((getXmlString().getBytes())));
//	    Autnresponse autnresponse = (Autnresponse) um.unmarshal(new FileReader(DATA_FILE));
	    System.out.println("Extract Content ======");
  }
  
	private static String getXmlString(){
		String result = "<?xml version='1.0' encoding='UTF-8' standalone='yes' ?>"
				+				"<autn:content xmlns:autn='http://schemas.autonomy.com/aci/'>"
				+					"<DOCUMENT>"
				+						"<DESCRIPTION>"
				+							"Consumer &amp; Home OfficeCamerasEOS CamerasEOS Rebel T5i with EF-S 18-55mm IS STM Kit"
				+						"</DESCRIPTION>"
				+					"</DOCUMENT>"
				+				"</autn:content>";
		
		return result;
  }
  
}
