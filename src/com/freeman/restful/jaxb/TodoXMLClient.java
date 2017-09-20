package com.freeman.restful.jaxb;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.freeman.restful.model.Product;
import com.freeman.restful.model.ProductFamily;
import com.freeman.restful.model.ProductFamilyGroup;
import com.freeman.restful.model.ProductGroup;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class TodoXMLClient {

	public static void main(String[] args) throws JAXBException, UniformInterfaceException, ClientHandlerException, IOException {
	    ClientConfig config = new DefaultClientConfig();
	    Client client = Client.create(config);
	    WebResource service = client.resource(getBaseURI());
	    // Get XML
	    String familiesInfo = service.path("famid").accept(MediaType.APPLICATION_XML).get(String.class);
	    String productsByFamilyInfo = service.path("pid/1").accept(MediaType.APPLICATION_XML).get(String.class);
	    
	    InputStream pgis = new ByteArrayInputStream(familiesInfo.getBytes("UTF-8"));
	    InputStream pis = new ByteArrayInputStream(productsByFamilyInfo.getBytes("UTF-8"));
	    System.out.println(familiesInfo);
	    System.out.println(productsByFamilyInfo);

	    System.out.println("start of Family List");
	    JAXBContext context = JAXBContext.newInstance(ProductFamilyGroup.class);
	    Unmarshaller um = context.createUnmarshaller();
	    ProductFamilyGroup fg = new ProductFamilyGroup();
	    fg = (ProductFamilyGroup) um.unmarshal(pgis);
	    for (ProductFamily f : fg.getProductFamilyList()){
	    	System.out.println(f.getId() + "--" + f.getFamilyName());
	    }
	    System.out.println("end of Family List");
	    System.out.println("-------------------------");
	    
	    System.out.println("start of Product List by famid = 1");
	    context = JAXBContext.newInstance(ProductGroup.class);
	    um = context.createUnmarshaller();
	    ProductGroup pg = new ProductGroup();
	    pg = (ProductGroup) um.unmarshal(pis);
	    for (Product p : pg.getProductList()){
	    	System.out.println(p.getPid() + "--" + p.getProductName());
	    }
	    System.out.println("end of Product List");
	    Marshaller m = context.createMarshaller();
	    m.setProperty(m.JAXB_ENCODING, "ISO8859-1");
	    m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

	    // Write to System.out
	    m.marshal(pg, System.out);	    

	    System.out.println("end");
	}

	private static URI getBaseURI() {
		return UriBuilder.fromUri("http://data.oce.com/lookup").build();
	}

}
