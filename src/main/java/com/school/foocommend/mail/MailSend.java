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
			msg.setContent("<a href='http://localhost:8080/foocommend/member/mailAuth?uid="+uid+"'>인증하기</a>", "text/html;charset=UTF-8");
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
