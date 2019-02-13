package com.boatguys.buisiness;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

public class Mail
{
    private  String smtpHost = "smtp.rambler.ru";
    private  String smtpPort = "25";
    private  String fromEmail = "boatguys@rambler.ru";
    private  String password = "SexDrugsOarsBoats0";

    private Session session;

    public Mail()
    {

        /*Properties props = new Properties();
        props.put("mail.smtp.host", "localhost"); //SMTP Host
        props.put("mail.smtp.port", smtpPort); //TLS Port
        /*props.put("mail.smtp.auth", "true");    //enable authentication
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.ssl.enable", "true");
        Authenticator auth = new Authenticator()
        {
            @Override
            protected PasswordAuthentication getPasswordAuthentication()
            {
                return new PasswordAuthentication(fromEmail, password);
            }
        };

        //session = Session.getDefaultInstance(props, auth);
        session = Session.getDefaultInstance(props);*/

    }

    public  void send(String subject, String message, String toEmail)
    {
        /*try
        {
            MimeMessage msg = new MimeMessage(session);
            //set message headers
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");

            msg.setFrom(new InternetAddress(fromEmail, "NoReply-JD"));

            msg.setReplyTo(InternetAddress.parse(fromEmail, false));

            msg.setSubject(subject, "UTF-8");

            msg.setText(message, "UTF-8");

            msg.setSentDate(new Date());

            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));

            Transport.send(msg);
        }
        catch (Exception e) {
            e.printStackTrace();
        }*/
    }
}
