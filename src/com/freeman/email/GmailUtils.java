package com.freeman.email;
import java.io.File;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


public class GmailUtils {
	public static void main(String[] args) {
		try {
			// No attachments
			new GmailUtils().sendSimpleMail("clement.echo@gmail.com","freeman351", "Test from Java", "hong_li@canada.canon.com","clement.echo@gmail.com", "Testing...", null);
			
			// One attachment
			new GmailUtils().sendSimpleMail("clement.echo@gmail.com","freeman351", "Test from Java", "hong_li@canada.canon.com","clement.echo@gmail.com", "Testing...",new String[] { "C:/IWR.xlsx" });
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public void sendSimpleMail(String username, String password,String subject, String to, 
			String from, String messageText,String[] attachmentPaths) throws AddressException, MessagingException {
		
		Properties mailProps = new Properties();
		mailProps.put("mail.transport.protocol", "smtp");
		mailProps.put("mail.smtp.starttls.enable", "true");
		mailProps.put("mail.smtp.host", "smtp.gmail.com");
		mailProps.put("mail.smtp.auth", "true");
		mailProps.put("mail.smtp.port", "465");
		mailProps.put("mail.smtp.debug", "true");
		mailProps.put("mail.mime.charset", "UTF-8");
		mailProps.put("mail.smtp.socketFactory.port", "465");
		mailProps.put("mail.smtp.socketFactory.fallback", "false");
		mailProps.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

		Authenticator auth = new GMailAuthenticator(username, password);
		Session session = Session.getDefaultInstance(mailProps, auth);

		Message message = new MimeMessage(session);

		message.setFrom(new InternetAddress(from));
		message.setRecipient(Message.RecipientType.TO,
				new InternetAddress(to));

		message.setSubject(subject);

		// Prepare the content for message + possible attachments
		BodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setContent(messageText, "text/html");

		// Container for all parts, including at least message and possibly attachments
		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart);

		for (int i = 0; (attachmentPaths != null) && (i < attachmentPaths.length); i++) {
			addAttachment(multipart, attachmentPaths[i]);
		}

		message.setContent(multipart);

		Transport.send(message);
	}

	private void addAttachment(Multipart multipart, String attachmentPath) throws MessagingException {
		MimeBodyPart attachmentPart = new MimeBodyPart();
		DataSource source = new FileDataSource(attachmentPath);
		attachmentPart.setDataHandler(new DataHandler(source));
		attachmentPart.setFileName(new File(attachmentPath).getName());
		multipart.addBodyPart(attachmentPart);
	}

	private class GMailAuthenticator extends Authenticator {
		private String username;
		private String password;

		public GMailAuthenticator(String username, String password) {
			this.username = username;
			this.password = password;
		}

		public PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(username, password);
		}
	}
	
}