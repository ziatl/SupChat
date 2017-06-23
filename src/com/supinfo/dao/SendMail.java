package com.supinfo.dao;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class SendMail {

   public static void SendEmail(String email) {    
	   final String username = "supquirk2017@gmail.com";
		final String password = "Supinfo07";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(email));
			message.setSubject("Reinitialisation de mot de passe");
			message.setText("Bonjour Crawler,"
				+ "\n Veuillez recevoir ci join votre nouveau mot de passe de connexion."
				+ "\n\n mpd : nouveauMotDepasse"
				+ "\n\n Cordialement,"
				+ "\n Quirk 2017.");
			

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
   }
}
