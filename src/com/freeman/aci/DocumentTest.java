package com.freeman.aci;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.freeman.aci.model.Document2;



public class DocumentTest {

	public static void main(String[] args) throws JAXBException, IOException {


	    // get variables from our xml file, created before
	    JAXBContext context = JAXBContext.newInstance(Document2.class);
	    Unmarshaller um = context.createUnmarshaller();
	    Document2 autnresponse = (Document2) um.unmarshal(new ByteArrayInputStream((getXmlString().getBytes())));
	    System.out.println("Extract Content ======");
  }
  
	private static String getXmlString(){
		String result = "<?xml version='1.0' encoding='UTF-8' standalone='yes' ?>"
				+					"<DOCUMENT>"
				+						"<DESCRIPTION>"
				+							"Consumer &amp; Home OfficeCamerasEOS CamerasEOS Rebel T5i with EF-S 18-55mm IS STM Kit"
				+						"</DESCRIPTION>"
				+					"</DOCUMENT>";
		
		return result;
  }
  
}
