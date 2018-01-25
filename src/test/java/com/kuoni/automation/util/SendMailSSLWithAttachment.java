package com.kuoni.automation.util;

import com.sun.org.apache.xpath.internal.operations.Mult;
import cucumber.api.java.eo.Se;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class SendMailSSLWithAttachment {


    public void sendEmail() throws AddressException {

        Properties props = new Properties();
        props.put("mail.smtp.host","smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port","465");
        props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth","true");
        props.put("mail.smtp.port","465");

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator(){
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("sridharv.ec@gmail.com","pinki510");
                    }
                });


        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("sridharv.ec@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("sridharv.ec@gmail.com"));
            message.setSubject("Test subject");
            BodyPart messageBodyPart1 = new MimeBodyPart();
            messageBodyPart1.setText("This is message body");
            MimeBodyPart messageBodyPart2 = new MimeBodyPart();
            String fileName = "E:\\Sridhar\\AutomationFolder\\AutomationBySridhar\\Frameworks\\cucumber-jvm-testng-integration-master\\target\\Reports\\report.html\\index.html";
            DataSource source = new FileDataSource(fileName);
            messageBodyPart2.setDataHandler(new DataHandler(source));
            messageBodyPart2.setFileName(fileName);
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart2);
            multipart.addBodyPart(messageBodyPart1);
            message.setContent(multipart);
            Transport.send(message);
            System.out.println("============Email sent==============");

        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws AddressException {
        SendMailSSLWithAttachment sendMailSSLWithAttachment = new SendMailSSLWithAttachment();
        sendMailSSLWithAttachment.sendEmail();
    }


}
