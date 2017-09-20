package com.freeman.aci;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.freeman.aci.model.HitData2;

public class HitDataTest {

	private static final String DATA_FILE = "./AutnResponse.xml";

	public static void main(String[] args) throws JAXBException, IOException {


	    // get variables from our xml file, created before
	    JAXBContext context = JAXBContext.newInstance(HitData2.class);
	    Unmarshaller um = context.createUnmarshaller();
	    HitData2 autnresponse = (HitData2) um.unmarshal(new ByteArrayInputStream((getXmlString().getBytes())));
//	    Autnresponse autnresponse = (Autnresponse) um.unmarshal(new FileReader(DATA_FILE));
	    System.out.println("Extract Content ======");
	}
  
	private static String getXmlString(){
		String result = "<?xml version='1.0' encoding='UTF-8' standalone='yes' ?>"
				+			"<autn:hit xmlns:autn='http://schemas.autonomy.com/aci/'>"
				+				"<autn:reference>/inetCA/products?m=gp&amp;pid=24236</autn:reference>"
				+				"<autn:id>70054</autn:id>"
				+				"<autn:section>7</autn:section>"
				+				"<autn:weight>82.34</autn:weight>"
				+				"<autn:links>EOS</autn:links>"
				+				"<autn:database>iNetCA_ConsumerEng</autn:database>"		
				+				"<autn:title>EOS Rebel T5i with EF-S 18-55mm IS STM Kit - Canon Canada Inc.</autn:title>"
				+				"<autn:content>"
				+					"<DOCUMENT>"
				+						"<DESCRIPTION>"
				+							"Consumer &amp; Home OfficeCamerasEOS CamerasEOS Rebel T5i with EF-S 18-55mm IS STM Kit"
				+						"</DESCRIPTION>"
				+					"</DOCUMENT>"
				+				"</autn:content>"
				+			"</autn:hit>"
				+ "";
		
		return result;
  }
  
}
