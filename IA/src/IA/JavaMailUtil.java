package IA;

import java.lang.System.Logger.Level;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.Properties;
import java.util.logging.Logger;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class JavaMailUtil {
	public static void sendMail(String recepient) throws Exception{
		System.out.println("Preparing to send email");
		Properties properties = new Properties();
		
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");
		
		String accountEmail = "kanoon.chaiyawan@gmail.com";
		String password = "*****";
		
		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(accountEmail, password);
			}
		});
		
		Message message = prepareMessage(session, accountEmail, recepient);
		
		Transport.send(message);
		System.out.println("Message sent successfully");
	}
	
	private static Message prepareMessage(Session session, String accountEmail, String recepient) {
		try{
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(accountEmail));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
			message.setSubject("Send Email");
			message.setText("Here is this content.");
			return message;
		} catch (Exception ex) {
			Logger.getLogger(JavaMailUtil.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
		
	}
}
