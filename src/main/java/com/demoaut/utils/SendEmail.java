package com.demoaut.utils;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;

import com.demoaut.base.TestBase;

public class SendEmail extends TestBase {

	//Create attachment
	public void mail() throws EmailException{
	EmailAttachment attachment = new EmailAttachment();
	String path = TestUtil.directory + "/" + TestUtil.date + ".pdf";
    attachment.setPath(path);
    attachment.setDisposition(EmailAttachment.ATTACHMENT);
    attachment.setDescription("Extent Report");
    
    //Creating email components
    MultiPartEmail email = new MultiPartEmail();
    email.setHostName(prop.getProperty("host"));
    email.setSmtpPort(465);
    email.setAuthenticator(new DefaultAuthenticator(prop.getProperty("user"), prop.getProperty("pwd")));
    email.setSSLOnConnect(true);
    email.setFrom(prop.getProperty("from"));
    email.setSubject(prop.getProperty("subject"));
    email.addTo(prop.getProperty("To"));
    email.attach(attachment);
    
    //Sending Email
    email.send();
    System.out.println("Email sent");
	}

}
