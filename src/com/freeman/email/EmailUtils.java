package com.freeman.email;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.URLName;

import org.apache.log4j.Logger;


public class EmailUtils {
	
	private static final String MSTORE_PROTOCOL = "pop3";
	private static final String EMAIL_INBOX = "INBOX";
	static Logger logger = Logger.getLogger(EmailUtils.class);

	public static void main(String[] args) throws MessagingException, IOException {
		
		String propertiesFilePath = "app_config/MeterCountConfig.properties";
		Properties properties = PropertiesUtils.loadProperties(propertiesFilePath);
		
		InputStream inputStream = (new EmailUtils()).getLastAttachment(properties, properties.getProperty(PropertiesUtils.EMAIL_ATTACHMENT_KEY));

		System.out.println("done = inputStream is " + (inputStream != null? "good" : "null"));
		
	}
	
	public InputStream getLastAttachment(Properties properties, String fileName){
		Store store = null;
		Folder inbox = null;
		InputStream is = null;
		try {
			Session session = Session.getDefaultInstance(new Properties());
			store = session.getStore(MSTORE_PROTOCOL);
			
			if (store == null) {
				throw new Exception ("No pop3 email store found in configuration.");
			}
			
			store.connect(properties.getProperty(PropertiesUtils.EMAIL_HOST_KEY), properties.getProperty(PropertiesUtils.EMAIL_USER_ID_KEY), properties.getProperty(PropertiesUtils.EMAIL_PASSWORD_KEY));
			
			inbox = store.getFolder(new URLName(EMAIL_INBOX));
			inbox.open(Folder.READ_WRITE);
			
			Message[] messages = inbox.getMessages();
			if (messages != null && messages.length > 0){
				for (int i = messages.length - 1; i >=0; i--){
					boolean isNewestMail = false;
					try {
						if (messages[i].getContent() instanceof Multipart){
							Multipart multipart = (Multipart) messages[i].getContent();
							
						    for (int j = 0; j < multipart.getCount(); j++) {
						        BodyPart bodyPart = multipart.getBodyPart(j);
						        if(!Part.ATTACHMENT.equalsIgnoreCase(bodyPart.getDisposition())
						        		|| bodyPart.getFileName() == null 
						        		|| !bodyPart.getFileName().equalsIgnoreCase(fileName)
					        			|| is != null) {
						            continue;
						        }						        
						        is = bodyPart.getInputStream();
						        isNewestMail = true;
								logger.info("=== Get a email with attachment file named : " + fileName + "!!! ===");
						        break;
						    }
						}
						if (!isNewestMail){
							messages[i].setFlag(Flags.Flag.DELETED, true);
						}
						
					} catch (IOException | MessagingException e) {
						e.printStackTrace();
					}			
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		try{
			if(inbox != null) {
				inbox.close(true);
			}
			inbox = null;
			
			if(store != null) {
				store.close();
			}
			store = null;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return is;		
	}
}
