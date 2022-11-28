package com.zinkworks.notification.service.email.poc.service.impl;

import com.zinkworks.notification.service.email.poc.model.EmailNotificationRequest;
import com.zinkworks.notification.service.email.poc.properties.EmailProperties;
import com.zinkworks.notification.service.email.poc.service.api.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@CommonsLog
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender emailSender;
    private final EmailProperties emailProperties;

    @Autowired
    EmailServiceImpl(JavaMailSender emailSender, EmailProperties emailProperties) {
        this.emailSender = emailSender;
        this.emailProperties = emailProperties;
    }

    @Override
    public void sendEmail(EmailNotificationRequest request) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(emailProperties.getMailAddress());
        message.setTo(request.getTo());

        message.setSubject(request.getEmailNotification().getSubject());
        message.setText(request.getEmailNotification().getBody());

        emailSender.send(message);
    }

    @Override
    public void sendEmailWithAttachment(EmailNotificationRequest request) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom(emailProperties.getMailAddress());
        helper.setTo(request.getTo());

        message.setSubject(request.getEmailNotification().getSubject());
        message.setText(request.getEmailNotification().getBody());

        helper.addAttachment(request.getAttachment().getFileName(), request.getAttachment().getFile());
        emailSender.send(message);
    }

}
