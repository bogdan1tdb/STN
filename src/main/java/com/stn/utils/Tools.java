package com.stn.utils;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Tools {

    public static String getDate() {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public void sendEmail(String to, String subject, String body) throws MessagingException {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("schoolingthenet","as5235$#%RETR");
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("no-reply@schoolingtheinter.net"));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        message.setSubject(subject);
        message.setContent(body, "text/html; charset=utf-8");

        Transport.send(message);
    }

}

