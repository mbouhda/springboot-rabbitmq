package com.mbouhda.consumer.tools;

import com.mbouhda.consumer.config.MailProperties;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
public class MailSender {

    private JavaMailSender javaMailSender;
    private MailProperties mailProperties;

    public MailSender(JavaMailSender javaMailSender, MailProperties mailProperties) {
        this.javaMailSender = javaMailSender;
        this.mailProperties = mailProperties;
    }

    public void sendMailMultipart(String toEmail, String subject, String message) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom(mailProperties.getFrom());
        helper.setTo(toEmail);
        helper.setSubject(subject);
        helper.setText(message);
        javaMailSender.send(mimeMessage);
    }
}
