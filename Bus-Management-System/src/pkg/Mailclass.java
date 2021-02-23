package pkg;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mailclass {

	
	public static void sendmail(String to, String text) {
        
	      String from = "busmanagementsystem123@gmail.com";
	 
	      
	      Properties properties = new Properties();
	 
	      properties.put("mail.smtp.auth", "true");
	      properties.put("mail.smtp.starttls.enable", "true");
	      properties.put("mail.smtp.host", "smtp.gmail.com");
	      properties.put("mail.smtp.port", "587");
	 
	     
	      Session session = Session.getInstance(properties, new Authenticator() {
	       protected PasswordAuthentication getPasswordAuthentication() {
	    	  return new PasswordAuthentication("busmanagementsystem123@gmail.com","nipun@123");
	       }
	      });
	    	  
	      
	      
	      try {
	          // Create a default MimeMessage object.
	          MimeMessage message = new MimeMessage(session);
	          
	          // Set From: header field of the header.
	          message.setFrom(new InternetAddress(from));
	          
	          // Set To: header field of the header.
	          message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
	          
	          // Set Subject: header field
	          message.setSubject("This is mail from Bus-System");
	          
	          // Now set the actual message
	          message.setText(text);
	          
	          // Send message
	          Transport.send(message);
	           
	       } 
	      
	      catch (MessagingException mex) 
	       {
	          mex.printStackTrace();
	       }
	    

	}
	
	
	
	
}
