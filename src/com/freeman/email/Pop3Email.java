package com.freeman.email;

import java.io.InputStream;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.URLName;


public class Pop3Email {

	private static final String EMAIL_HOST = "lnccim01.canoncanada.canon.com";
	private static final String EMAIL_USER_ID = "Q04706";
	private static final String EMAIL_PASSWORD = "canon123";
	
	private static final String MSTORE_PROTOCOL = "pop3";
	private static final String EMAIL_INBOX = "INBOX";

	public static void main(String[] args) throws MessagingException {
		
		Store store = null;
		Folder folder = null;
		
		// Validate user authorizations.
		try {

			Session session = Session.getDefaultInstance(new Properties());
			store = session.getStore(MSTORE_PROTOCOL);
			
			if (store == null) {
				throw new Exception ("No pop3 email store found in configuration.");
			}
			
			store.connect(EMAIL_HOST, EMAIL_USER_ID, EMAIL_PASSWORD);
			
			folder = store.getFolder(new URLName(EMAIL_INBOX));
			folder.open(Folder.READ_WRITE);
			
			Message[] messages = folder.getMessages();
			int numberMessagesToProcess = messages.length;
			
			
			if(numberMessagesToProcess == 0){
				folder.close(true);
				store.close();
				folder = null;
				store = null;
			}

			//Copy and  Mark mail message as deleted.
			for (Message message : messages) {
				System.out.println("email from: " + message.getFrom()[0]);
				System.out.println("send Date: " + message.getSentDate());
				if (message.getContent() instanceof String){
					System.out.println(((String) message.getContent()));
				}
				if (message.getContent() instanceof Multipart){
					Multipart multipart = (Multipart) message.getContent();
					
				    System.out.println(multipart.getCount());
				    for (int i = 0; i < multipart.getCount(); i++) {
				        BodyPart bodyPart = multipart.getBodyPart(i);
				        System.out.println(bodyPart.getFileName());
				        if(!Part.ATTACHMENT.equalsIgnoreCase(bodyPart.getDisposition())) {
				            continue; // dealing with attachments only
				        } 
				        InputStream is = bodyPart.getInputStream();
						System.out.println("inputStream is " + (is != null? "good" : "null" ));
				    }
				}
//				message.setFlag(Flags.Flag.DELETED, true);
			}
			System.out.println("done");
		} 
		catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if(folder != null) {
					folder.close(true);
				}
				if(store != null) {
					store.close();
				}
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		}
	}

}
