package tbcs.utility;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.sun.xml.internal.ws.api.message.MessageWritable;

import tbcs.model.Email;
import tbcs.model.accountBean;

public class emailer {

	
	public void sendEmail()
	{
		try{
			final Email email = new Email();
			
			//Not Working
			// sender account (SYSTEM EMAIL)
			email.setFromAddress("systemteamavengers@gmail.com");
			email.setFromPassword("teamavengers");	
			
			// receiver account (ADMIN EMAIL)
			email.setToAddress("janine.leaaa@gmail.com");
					  
			Properties emailProperties = new Properties();	
			emailProperties.put("mail.smtp.starttls.enable", "true");
			emailProperties.put("mail.smtp.host", "smtp.gmail.com");
			emailProperties.put("mail.smtp.port", "587");
			emailProperties.put("mail.smtp.auth", "true");
			emailProperties.put("mail.debug", "true");
			emailProperties.put("mail.smtp.user", email.getFromAddress());
			emailProperties.put("mail.smtp.password", email.getFromPassword());
			
			Session session = Session.getInstance(emailProperties,
			      new javax.mail.Authenticator() {
			          protected PasswordAuthentication getPasswordAuthentication() {
			               return new PasswordAuthentication(email.getFromAddress(), email.getFromPassword());
			        }
			      });
	        
	        try{
	        	MimeMessage message = new MimeMessage(session);
	        	message.setFrom(new InternetAddress(email.getFromAddress(), "Traffic Billing and Collection System"));
	        	message.setRecipients(Message.RecipientType.TO,
	                InternetAddress.parse(email.getToAddress()));
	        	message.setSubject("Notification for approval");
	        	message.setText("There's a new client request. Waiting for your approval");
	        	message.setText("GO here to approve the accounts. http://localhost:8080/Thesis/viewadvertisers.html");
		        
	        	 Transport transport = session.getTransport("smtp");
	             transport.connect("smtp.gmail.com", email.getFromAddress(), email.getFromPassword());
	            
	             
	             
	             message.saveChanges();
	             Transport.send(message);
	             transport.close();
	        	
	        }catch(MessagingException e){
	        	throw new RuntimeException(e);
	        }
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	
	public void sendEmail(String advetiserEmail)
	{
		try{
			final Email email = new Email();
			
			//Not Working
			// sender account (SYSTEM EMAIL)
			email.setFromAddress("systemteamavengers@gmail.com");
			email.setFromPassword("teamavengers");	
			
			// receiver account (ADMIN EMAIL)
			email.setToAddress(advetiserEmail);
					  
			Properties emailProperties = new Properties();	
			emailProperties.put("mail.smtp.starttls.enable", "true");
			emailProperties.put("mail.smtp.host", "smtp.gmail.com");
			emailProperties.put("mail.smtp.port", "587");
			emailProperties.put("mail.smtp.auth", "true");
			emailProperties.put("mail.debug", "true");
			emailProperties.put("mail.smtp.user", email.getFromAddress());
			emailProperties.put("mail.smtp.password", email.getFromPassword());
			
			Session session = Session.getInstance(emailProperties,
			      new javax.mail.Authenticator() {
			          protected PasswordAuthentication getPasswordAuthentication() {
			               return new PasswordAuthentication(email.getFromAddress(), email.getFromPassword());
			        }
			      });
	        
	        try{
	        	MimeMessage message = new MimeMessage(session);
	        	message.setFrom(new InternetAddress(email.getFromAddress(), "Traffic Billing and Collection System"));
	        	message.setRecipients(Message.RecipientType.TO,
	                InternetAddress.parse(email.getToAddress()));
	        	message.setSubject("Your Account has been approved");
	        	message.setText("There's a new client request. Waiting for your approval");
		        
	        	 Transport transport = session.getTransport("smtp");
	             transport.connect("smtp.gmail.com", email.getFromAddress(), email.getFromPassword());
	            
	             
	             
	             message.saveChanges();
	             Transport.send(message);
	             transport.close();
	        	
	        }catch(MessagingException e){
	        	throw new RuntimeException(e);
	        }
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void sendEmail(String advetiserEmail, accountBean account)
	{
		try{
			final Email email = new Email();
			
			//Not Working
			// sender account (SYSTEM EMAIL)
			email.setFromAddress("systemteamavengers@gmail.com");
			email.setFromPassword("teamavengers");	
			
			// receiver account (ADMIN EMAIL)
			email.setToAddress(advetiserEmail);
					  
			Properties emailProperties = new Properties();	
			emailProperties.put("mail.smtp.starttls.enable", "true");
			emailProperties.put("mail.smtp.host", "smtp.gmail.com");
			emailProperties.put("mail.smtp.port", "587");
			emailProperties.put("mail.smtp.auth", "true");
			emailProperties.put("mail.debug", "true");
			emailProperties.put("mail.smtp.user", email.getFromAddress());
			emailProperties.put("mail.smtp.password", email.getFromPassword());
			
			Session session = Session.getInstance(emailProperties,
			      new javax.mail.Authenticator() {
			          protected PasswordAuthentication getPasswordAuthentication() {
			               return new PasswordAuthentication(email.getFromAddress(), email.getFromPassword());
			        }
			      });
	        
	        try{
	        	MimeMessage message = new MimeMessage(session);
	        	message.setFrom(new InternetAddress(email.getFromAddress(), "Traffic Billing and Collection System"));
	        	message.setRecipients(Message.RecipientType.TO,
	                InternetAddress.parse(email.getToAddress()));
	        	message.setSubject("Your Account has been created");
	        	message.setText("Your account for Traffic Billing Collection System has been created with the following details. Username: " +
	        	account.getUsername() + " Password: " + account.getPassword() + " Thank you for registering at our Customer Management System."
	        	);
		        
	        	 Transport transport = session.getTransport("smtp");
	             transport.connect("smtp.gmail.com", email.getFromAddress(), email.getFromPassword());
	            
	             
	             
	             message.saveChanges();
	             Transport.send(message);
	             transport.close();
	        	
	        }catch(MessagingException e){
	        	throw new RuntimeException(e);
	        }
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void sendEmail(String recepient, String subject, String emailMessage)
	{
		try{
			final Email email = new Email();
			
			//Not Working
			// sender account (SYSTEM EMAIL)
			email.setFromAddress("systemteamavengers@gmail.com");
			email.setFromPassword("teamavengers");	
			
			// receiver account (ADMIN EMAIL)
			email.setToAddress(recepient);
					  
			Properties emailProperties = new Properties();	
			emailProperties.put("mail.smtp.starttls.enable", "true");
			emailProperties.put("mail.smtp.host", "smtp.gmail.com");
			emailProperties.put("mail.smtp.port", "587");
			emailProperties.put("mail.smtp.auth", "true");
			emailProperties.put("mail.debug", "true");
			emailProperties.put("mail.smtp.user", email.getFromAddress());
			emailProperties.put("mail.smtp.password", email.getFromPassword());
			
			Session session = Session.getInstance(emailProperties,
			      new javax.mail.Authenticator() {
			          protected PasswordAuthentication getPasswordAuthentication() {
			               return new PasswordAuthentication(email.getFromAddress(), email.getFromPassword());
			        }
			      });
	        
	        try{
	        	MimeMessage message = new MimeMessage(session);
	        	message.setFrom(new InternetAddress(email.getFromAddress(), "Traffic Billing and Collection System"));
	        	message.setRecipients(Message.RecipientType.TO,
	                InternetAddress.parse(email.getToAddress()));
	        	message.setSubject(subject);
	        	message.setText(emailMessage);
		        
	        	 Transport transport = session.getTransport("smtp");
	             transport.connect("smtp.gmail.com", email.getFromAddress(), email.getFromPassword());
	            
	             
	             
	             message.saveChanges();
	             Transport.send(message);
	             transport.close();
	        	
	        }catch(MessagingException e){
	        	throw new RuntimeException(e);
	        }
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
