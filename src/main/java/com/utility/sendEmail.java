
package com.utility;


import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
/**
 *
 * @author amirk
 */
public class sendEmail {
    public static boolean sendEmail(String title,String mmessage,String to){
      String from = "web@gmail.com";
      String host = "localhost";
      Properties properties = System.getProperties();

      properties.setProperty("mail.smtp.host", host);
      Session session = Session.getDefaultInstance(properties);

      try{
         MimeMessage message = new MimeMessage(session);
         message.setFrom(new InternetAddress(from));
         message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
         message.setSubject(title);
         message.setText(mmessage);
         Transport.send(message);
         System.out.println("Sent message successfully....");
         return true;
      }catch (MessagingException mex) {
         mex.printStackTrace();
         return false;
      }
    }
    
}
