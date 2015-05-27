package com.razborka.util;

import com.razborka.Constants;
import com.razborka.model.User;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by Admin on 13.05.2015.
 */
public class EmailSender {
    private String username;
    private String password;
    private Properties props;

    public EmailSender() {
        this.username = Constants.EMAIL_LOGIN;
        this.password = Constants.EMAIL_PASSWORD;

        props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.mime.charset", "UTF-8");
    }

    public void send(User user, int TYPE) {
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            String text = getMessage(TYPE, user.getEmail(), user.getPassword());
            String subject = getSubject(TYPE);

            Message message = new MimeMessage(session);
            message.setHeader("Content-Type", "text/html; charset=UTF-8");
            //от кого
            message.setFrom(new InternetAddress(username));
            //кому
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(user.getEmail()));
            //Заголовок письма
            message.setSubject(subject);
            //Содержимое
            //message.setText(text);
            message.setContent(text, "text/html; charset=UTF-8");

            //Отправляем сообщение
            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public String getMessage(int TYPE, String email, String password) {
        StringBuilder messageText = new StringBuilder();
        switch (TYPE) {
            case Constants.REGISTRATION:
                messageText.append("Вы были успешно зарегистрированы на " + Constants.SITE_NAME + "<br><br>");
                messageText.append("Используйте данные указанные ниже для авторизации на сайте:<br>");
                messageText.append("Email: " + email + " <br>");
                messageText.append("Пароль: " + password + " <br>");
                messageText.append("<br>");
                messageText.append("<b>Администрация Razborka.by</b>");
                break;
            case Constants.NEW_MESSAGE:
                messageText.append("У Вас новое сообщение<br>");
                messageText.append("<br>");
                messageText.append("<b>Администрация Razborka.by</b>");
                break;
            case Constants.NEW_COMMENT:
                messageText.append("У Вас новый комментарий<br>");
                messageText.append("<br>");
                messageText.append("<b>Администрация Razborka.by</b>");
                break;
            case Constants.NEW_REVIEW:
                messageText.append("У Вас новый отзыв<br>");
                messageText.append("<br>");
                messageText.append("<b>Администрация Razborka.by</b>");
                break;
            case Constants.NEW_REQUEST:
                messageText.append("Новый запрос от пользователя<br>");
                messageText.append("<br>");
                messageText.append("<b>Администрация Razborka.by</b>");
                break;
        }
        return  messageText.toString();
    }

    public String getSubject(int TYPE) {
        String subject = "";
        switch (TYPE) {
            case Constants.REGISTRATION:
                subject = "Регистрация на " + Constants.SITE_NAME;
                break;
            case Constants.NEW_MESSAGE:
                subject = "Новое сообщение на " + Constants.SITE_NAME;
                break;
            case Constants.NEW_COMMENT:
                subject = "Новый комментарий на " + Constants.SITE_NAME;
                break;
            case Constants.NEW_REVIEW:
                subject = "Новый отзыв на " + Constants.SITE_NAME;
                break;
            case Constants.NEW_REQUEST:
                subject = "Новый запрос на " + Constants.SITE_NAME;
                break;
        }
        return  subject;
    }
}
