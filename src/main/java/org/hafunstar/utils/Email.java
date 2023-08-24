package org.hafunstar.utils;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class Email {

    public static boolean sendMail(String toMail,String context) {
        // 收件人电子邮箱
        String to = toMail;

        // 发件人电子邮箱
        String from = "Lowrec332@hotmail.com";

        String password = "kang147op;";
        // 指定发送邮件的主机为 localhost
        String host = "smtp.office365.com";

        Properties props = new Properties();
        props.put("mail.smtp.host", host); // SMTP主机名
        props.put("mail.smtp.port", "587"); // 主机端口号
        props.put("mail.smtp.auth", "true"); // 是否需要用户认证
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        try{
            // 创建默认的 MimeMessage 对象
            MimeMessage message = new MimeMessage(session);

            // Set From: 头部头字段
            message.setFrom(new InternetAddress(from));

            // Set To: 头部头字段
            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(to));

            // Set Subject: 头部头字段
            message.setSubject("用户激活","UTF-8");

            // 设置消息体
            message.setContent(context,
                    "text/html;charset=UTF-8" );

            // 发送消息
            Transport.send(message);
            return true;
        }catch (MessagingException mex) {
            mex.printStackTrace();
            System.out.println("未发送成功邮箱:"+toMail);
            Email.sendMail(toMail,context);
            return false;
        }
    }
}

