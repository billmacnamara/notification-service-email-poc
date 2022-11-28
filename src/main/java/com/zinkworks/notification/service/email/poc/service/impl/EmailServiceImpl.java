package com.zinkworks.notification.service.email.poc.service.impl;

import com.zinkworks.notification.service.email.poc.exception.MissingRecipientException;
import com.zinkworks.notification.service.email.poc.model.EmailNotificationRequest;
import com.zinkworks.notification.service.email.poc.properties.EmailProperties;
import com.zinkworks.notification.service.email.poc.service.api.EmailService;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
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
        if (request.getTo() == null || request.getTo().length == 0 ) {
            throw new MissingRecipientException("No email recipient was provided");
        }

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(emailProperties.getMailSender());
        message.setTo(request.getTo());

        message.setSubject(request.getEmailNotification().getSubject());
        message.setText(request.getEmailNotification().getBody());

        emailSender.send(message);
    }

}
