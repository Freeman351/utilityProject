package com.freeman.utilities;

import java.io.ByteArrayInputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class JaxbXmlUtil {
	
	public static <T> T parseXmlString(String xmlString, Class<T> className) throws UnsupportedEncodingException, JAXBException{
		
	    JAXBContext context = JAXBContext.newInstance(className);
	    Unmarshaller um = context.createUnmarshaller();
		return (T) um.unmarshal(new ByteArrayInputStream(xmlString.getBytes("UTF-8")));
	}
	
	public static <T> String generateXmlString(T t) throws JAXBException{
		StringWriter sw = new StringWriter();
		sw.write("<?xml version=\"1.0\" encoding=\"utf-8\" ?>\r");
	    JAXBContext context = JAXBContext.newInstance(t.getClass());
	    Marshaller m = context.createMarshaller();
	    m.setProperty(Marshaller.JAXB_ENCODING, "utf-8");
	    m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	    m.setProperty("com.sun.xml.bind.xmlDeclaration", Boolean.FALSE);
	    m.marshal(t, sw);
	    return sw.toString();
	}
}
