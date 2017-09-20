package com.freeman.aci;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.autonomy.aci.AciAction;
import com.autonomy.aci.AciConnection;
import com.autonomy.aci.AciResponse;
import com.autonomy.aci.ActionParameter;
import com.autonomy.aci.constants.IDOLConstants;
import com.autonomy.aci.exceptions.AciException;
import com.freeman.aci.model.AutnResponse;



public class AutonomyContentUtil {

	public static final String ACI_SEARCH_PARAMETER_START = "start";
	public static final String ACI_SEARCH_PARAMETER_SEARCH_TEXT = "text";
	public static final String ACI_SEARCH_PARAMETER_MAX_RESULTS = "maxresults";
	public static final String ACI_SEARCH_PARAMETER_DATABASE_MATCH = "databasematch";	
	public static final String ACI_SEARCH_DATABASE_ENGLISH = "iNetCAEnglish";
	public static final String ACI_SEARCH_DATABASE_FRENCH = "iNetCAFrench";
	public static final String ACI_SEARCH_LANGUAGE_FRENCH = "fr";
	public static final String AMPERSAND_SIGN = "&";
	public static final String EQUALS_SIGN = "=";

	public static void main(String[] args) throws JAXBException, IOException, AciException {

		
	    // get variables from our xml file, created before
		AutnResponse autnResponse;
		String searchText = "EOS";
//		autnResponse = parseACIXmlString((getACIXmlResponse().toString()));
	    autnResponse = parseACIXmlString(getXmlString());
	    System.out.println("Extract Content ======");
	}
  
	private static String getXmlString(){
		String result = "<?xml version='1.0' encoding='UTF-8' standalone='yes' ?>"
				+ "<autnresponse xmlns:autn='http://schemas.autonomy.com/aci/'>"
				+ 		"<action>QUERY</action>" 
				+ 		"<response>SUCCESS</response>"
				+ 		"<responsedata>"
				+ 			"<autn:numhits>6</autn:numhits>"
				+			"<autn:totalhits>2001</autn:totalhits>"
				+			"<autn:hit>"
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
				+			"<autn:hit><autn:reference>/inetCA/products?m=gp&amp;pid=24236</autn:reference><autn:id>70048</autn:id><autn:section>1</autn:section><autn:weight>82.34</autn:weight><autn:links>EOS</autn:links><autn:database>iNetCA_ConsumerEng</autn:database><autn:title>EOS Rebel T5i with EF-S 18-55mm IS STM Kit - Canon Canada Inc.</autn:title><autn:content><DOCUMENT><DESCRIPTION>Consumer &amp; Home OfficeCamerasEOS CamerasEOS Rebel T5i with EF-S 18-55mm IS STM Kit</DESCRIPTION></DOCUMENT></autn:content></autn:hit>"
				+			"<autn:hit><autn:reference>/inetCA/products?m=gp&amp;pid=24236</autn:reference><autn:id>70047</autn:id><autn:section>0</autn:section><autn:weight>82.34</autn:weight><autn:links>EOS</autn:links><autn:database>iNetCA_ConsumerEng</autn:database><autn:title>EOS Rebel T5i with EF-S 18-55mm IS STM Kit - Canon Canada Inc.</autn:title><autn:content><DOCUMENT><DESCRIPTION>Consumer &amp; Home OfficeCamerasEOS CamerasEOS Rebel T5i with EF-S 18-55mm IS STM Kit</DESCRIPTION></DOCUMENT></autn:content></autn:hit>"
				+			"<autn:hit><autn:reference>/inetCA/products?m=gp&amp;pid=17958</autn:reference><autn:id>70028</autn:id><autn:section>0</autn:section><autn:weight>82.34</autn:weight><autn:links>EOS</autn:links><autn:database>iNetCA_ConsumerEng</autn:database><autn:title>EOS M with EF-M 18-55mm f/3.5-5.6 STM and Flash Kit - Canon Canada Inc.</autn:title><autn:content><DOCUMENT><DESCRIPTION>Consumer &amp; Home OfficeCamerasEOS CamerasEOS M with EF-M 18-55mm f/3.5-5.6 STM and Flash Kit</DESCRIPTION></DOCUMENT></autn:content></autn:hit>"
				+			"<autn:hit><autn:reference>/inetCA/products?m=gp&amp;pid=10456</autn:reference><autn:id>70001</autn:id><autn:section>8</autn:section><autn:weight>82.34</autn:weight><autn:links>EOS</autn:links><autn:database>iNetCA_ConsumerEng</autn:database><autn:title>EOS Rebel T3i with EF-S 18-55mm f/3.5-5.6 IS II Kit - Canon Canada Inc.</autn:title><autn:content><DOCUMENT><DESCRIPTION>Consumer &amp; Home OfficeCamerasEOS CamerasEOS Rebel T3i with EF-S 18-55mm f/3.5-5.6 IS II Kit</DESCRIPTION></DOCUMENT></autn:content></autn:hit>"
				+			"<autn:hit><autn:reference>/inetCA/products?m=gp&amp;pid=10456</autn:reference><autn:id>69993</autn:id><autn:section>0</autn:section><autn:weight>82.34</autn:weight><autn:links>EOS</autn:links><autn:database>iNetCA_ConsumerEng</autn:database><autn:title>EOS Rebel T3i with EF-S 18-55mm f/3.5-5.6 IS II Kit - Canon Canada Inc.</autn:title><autn:content><DOCUMENT><DESCRIPTION>Consumer &amp; Home OfficeCamerasEOS CamerasEOS Rebel T3i with EF-S 18-55mm f/3.5-5.6 IS II Kit</DESCRIPTION></DOCUMENT></autn:content></autn:hit>"
				+		"</responsedata>"
				+	"</autnresponse>";
		
		return result;
	}

	public static String generateAciURL(String idolHost, String idolPort, String baseQuery, String language, String searchText, long start, long perPage){
        StringBuffer searchUrlBuffer = new StringBuffer(idolHost + ":" + idolPort);
        if ( baseQuery != null && !baseQuery.trim().isEmpty()){
        	searchUrlBuffer.append("/" + baseQuery );  // /action=query&totalresults=true
        }        
        searchUrlBuffer.append(AMPERSAND_SIGN + ACI_SEARCH_PARAMETER_SEARCH_TEXT + EQUALS_SIGN +  searchText);  // &text=Eos
        searchUrlBuffer.append(AMPERSAND_SIGN + ACI_SEARCH_PARAMETER_MAX_RESULTS + EQUALS_SIGN +  (start + perPage));  // &maxresults=30
        if (start > 0){
            searchUrlBuffer.append(AMPERSAND_SIGN + ACI_SEARCH_PARAMETER_START + EQUALS_SIGN +  (start + 1));  // &start=0       	        	
        }
        if (language != null && language.equalsIgnoreCase(ACI_SEARCH_LANGUAGE_FRENCH)){
        	searchUrlBuffer.append(AMPERSAND_SIGN + ACI_SEARCH_PARAMETER_DATABASE_MATCH + EQUALS_SIGN + ACI_SEARCH_DATABASE_FRENCH);
        }else{
        	searchUrlBuffer.append(AMPERSAND_SIGN + ACI_SEARCH_PARAMETER_DATABASE_MATCH + EQUALS_SIGN + ACI_SEARCH_DATABASE_ENGLISH);
        }
		return searchUrlBuffer.toString();
	}
	
	public static AutnResponse parseACIXmlString(String aciXmlString) throws JAXBException, IOException{
		
	    JAXBContext context = JAXBContext.newInstance(AutnResponse.class);
	    Unmarshaller um = context.createUnmarshaller();
	    AutnResponse autnresponse = (AutnResponse) um.unmarshal(new ByteArrayInputStream(aciXmlString.getBytes("UTF-8")));
		return autnresponse;
	}
	
	public static AciResponse getACIXmlResponse() throws AciException{
		 String idolHost = "vasudv05.cusa.canon.com";
	     int idolPort = 9300;
	     
	     // Create a connection object
	     AciConnection aciConnection= new AciConnection();
	     
	     // Set host details
	     aciConnection.setAciHost(idolHost);
	     aciConnection.setAciPort(idolPort);
	     aciConnection.setRetries(1);
	     
	     AciAction aciAction = new AciAction(IDOLConstants.QUERY_ACTION);
	     ActionParameter action = new ActionParameter();
	     action.setName(IDOLConstants.TEXT_PARAM_NAME);
	     action.setValue("Eos");
	     aciAction.setParameter(action);
	     aciAction.setParameter(new ActionParameter(IDOLConstants.QUERY_TOTAL_RESULTS_NAME, true));
	     aciAction.setParameter(new ActionParameter(IDOLConstants.QUERY_MAX_RESULTS_PARAM_NAME, 10));
	     aciAction.setParameter(new ActionParameter(IDOLConstants.QUERY_START_PARAM_NAME, 2));

	     System.out.println(aciAction.toString());
	     aciAction.usePostHTTPMethod();
	     
	     // Execute the Search
	     AciResponse aciResponse = aciConnection.aciActionExecute(aciAction);
	     return aciResponse;
		
	}
}
