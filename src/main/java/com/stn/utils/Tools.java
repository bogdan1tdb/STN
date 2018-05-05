package com.stn.utils;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.text.SimpleDateFormat;

public class Tools {

    public static Timestamp getDate() {
        Timestamp date = new Timestamp(System.currentTimeMillis());
        return date;
    }

    public static String formatDate(Timestamp date,int type) {
        String s = "";
        if(type == 1)
            s = new SimpleDateFormat("HH:mm").format(date);
        else if(type == 2)
            s = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(date);
        else if(type == 3)
            s = new SimpleDateFormat("dd-MM-yyyy HH:mm").format(date);
        else if(type == 4)
            s = new SimpleDateFormat("dd-MM-yyyy").format(date);
        return s;
    }

    public Boolean userIsOnline(Timestamp lastSeen) {

        long timeout = 5 * 60 * 1000;
        java.sql.Timestamp current = new java.sql.Timestamp(new java.util.Date().getTime() - timeout );

        if(lastSeen.after(current))
            return true;
        else
            return false;
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

    public static String formatText(String text) {
        String html = text;

        Map<String,String> bbMap = new HashMap<String , String>();

        //Smilies

        bbMap.put(":\\)", "<img src='/img/smilies/smile1.gif'>");
        bbMap.put(":\\D", "<img src='/img/smilies/grin.gif'>");
        bbMap.put(":\\|", "<img src='/img/smilies/noexpression.gif'>");
        bbMap.put(":O", "<img src='/img/smilies/ohmy.gif'>");
        bbMap.put(":\\(", "<img src='/img/smilies/sad.gif'>");
        bbMap.put(":o\\)", "<img src='/img/smilies/clown.gif'>");
        bbMap.put(":beer:", "<img src='/img/smilies/beer.gif'>");

        //Text

        bbMap.put("(\r\n|\r|\n|\n\r)", "<br/>");
        bbMap.put("\\[b\\](.+?)\\[/b\\]", "<strong>$1</strong>");
        bbMap.put("\\[i\\](.+?)\\[/i\\]", "<i>$1</i>");
        bbMap.put("\\[u\\](.+?)\\[/u\\]", "<span style='text-decoration:underline;'>$1</span>");
        bbMap.put("\\[h1\\](.+?)\\[/h1\\]", "<h1>$1</h1>");
        bbMap.put("\\[h2\\](.+?)\\[/h2\\]", "<h2>$1</h2>");
        bbMap.put("\\[h3\\](.+?)\\[/h3\\]", "<h3>$1</h3>");
        bbMap.put("\\[h4\\](.+?)\\[/h4\\]", "<h4>$1</h4>");
        bbMap.put("\\[h5\\](.+?)\\[/h5\\]", "<h5>$1</h5>");
        bbMap.put("\\[h6\\](.+?)\\[/h6\\]", "<h6>$1</h6>");
        bbMap.put("\\[quote\\](.+?)\\[/quote\\]", "<blockquote>$1</blockquote>");
        bbMap.put("\\[p\\](.+?)\\[/p\\]", "<p>$1</p>");
        bbMap.put("\\[p=(.+?),(.+?)\\](.+?)\\[/p\\]", "<p style='text-indent:$1px;line-height:$2%;'>$3</p>");
        bbMap.put("\\[center\\](.+?)\\[/center\\]", "<div align='center'>$1</div>");
        bbMap.put("\\[align=(.+?)\\](.+?)\\[/align\\]", "<div align='$1'>$2</div>");
        bbMap.put("\\[color=(.+?)\\](.+?)\\[/color\\]", "<font color='$1'>$2</font>");
        bbMap.put("\\[size=(.+?)\\](.+?)\\[/size\\]", "<font size='$1'>$2</font>");
        bbMap.put("\\[img\\](.+?)\\[/img\\]", "<img src='$1' />");
        bbMap.put("\\[img=(.+?),(.+?)\\](.+?)\\[/img\\]", "<img width='$1' height='$2' src='$3' />");
        bbMap.put("\\[email\\](.+?)\\[/email\\]", "<a href='mailto:$1'>$1</a>");
        bbMap.put("\\[email=(.+?)\\](.+?)\\[/email\\]", "<a href='mailto:$1'>$2</a>");
        bbMap.put("\\[url\\](.+?)\\[/url\\]", "<a href='$1'>$1</a>");
        bbMap.put("\\[url=(.+?)\\](.+?)\\[/url\\]", "<a href='$1'>$2</a>");
        bbMap.put("\\[youtube\\](.+?)\\[/youtube\\]", "<object width='640' height='380'><param name='movie' value='http://www.youtube.com/v/$1'></param><embed src='http://www.youtube.com/v/$1' type='application/x-shockwave-flash' width='640' height='380'></embed></object>");
        bbMap.put("\\[video\\](.+?)\\[/video\\]", "<video src='$1' />");

        for (Map.Entry entry: bbMap.entrySet()) {
            html = html.replaceAll(entry.getKey().toString(), entry.getValue().toString());
        }

        return html;
    }

}

