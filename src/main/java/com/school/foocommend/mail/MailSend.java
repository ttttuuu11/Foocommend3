package com.school.foocommend.mail;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.google.protobuf.Message;

@Component
public class MailSend {
	
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	public void MailSend(String uid, String email) {
		Properties prop = System.getProperties();
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.port", "587");
		
		Authenticator auth = new MailAuth();
	
		Session session = Session.getDefaultInstance(prop,auth);
	
		MimeMessage msg = new MimeMessage(session);
		
		try {
			msg.setSentDate(new Date());
			msg.setFrom(new InternetAddress("ttttuuu205@gmail.com","Foocommend"));
			InternetAddress to = new InternetAddress(email);
			msg.setRecipient(javax.mail.Message.RecipientType.TO,to);
			msg.setSubject("[Foocommend]인증 메일입니다.","UTF-8");
			
			
			msg.setContent("<div class=\"mailBox\" onclick=\"location.href='http://localhost:8080/foocommend/member/mailAuth?uid='"+uid+";\"\r\n" + 
					"		style=\"width: 600px; height: 300px; text-align: center; background-color: #FF9797; \r\n" + 
					"		background-image:url('http://localhost:8080/foocommend/resources/ui_image/mail_background.jpg'); border-radius: 20px;\">\r\n" + 
					"		<br> <br>\r\n" + 
					"		<h1 class=\"whitefont\" style=\"vertical-align: middle; color: white;\">Foocommend</h1>\r\n" + 
					"		<h3 class=\"whitefont\" style=\"vertical-align: middle; color: white;\">클릭하면\r\n" + 
					"			인증이 완료됩니다!</h3>\r\n" + 
					"<a href='http://localhost:8080/foocommend/member/mailAuth?uid=\""+uid+"\"'>"
							+ "<img src='http://localhost:8080/foocommend/resources/ui_image/tap.png' width=\"dj0px\" height=\"80px\"/>\""
							+ "</a>"+
					"	</div>","text/html;charset=UTF-8");
			Transport.send(msg);
		}catch(AddressException  ae) {
			log.debug("AddressException:"+ae.getMessage());
		}catch(MessagingException me) {
			log.debug("MessageingException:"+me.getMessage());
		}catch(UnsupportedEncodingException e) {
			log.debug("UnsupportedEncodingException:"+e.getMessage());
		}
	}
}
