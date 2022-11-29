package com.zinkworks.notification.service.email.poc.service.impl;

import com.zinkworks.notification.service.email.poc.exception.MissingRecipientException;
import com.zinkworks.notification.service.email.poc.model.EmailNotificationRequest;
import com.zinkworks.notification.service.email.poc.properties.EmailProperties;
import com.zinkworks.notification.service.email.poc.service.api.EmailService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@CommonsLog
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender emailSender;
    private final EmailProperties emailProperties;
    private final Validator validator;

    @Autowired
    EmailServiceImpl(JavaMailSender emailSender, EmailProperties emailProperties, Validator validator) {
        this.emailSender = emailSender;
        this.emailProperties = emailProperties;
        this.validator = validator;
    }

    @Override
    public void sendEmail(EmailNotificationRequest request) {
        if (request.getTo() == null || request.getTo().length == 0 ) {
            throw new MissingRecipientException("No email recipient was provided");
        }

        this.validateEmailAddress(request.getTo());

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(emailProperties.getMailSender());
        message.setTo(request.getTo());

        message.setSubject(request.getEmailNotification().getSubject());
        message.setText(request.getEmailNotification().getBody());

        emailSender.send(message);
    }

    private void validateEmailAddress(String[] addresses) {
        Set<ConstraintViolation<String[]>> violations = this.validator.validate(addresses);

        if (!violations.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (ConstraintViolation<String[]> constraintViolation : violations) {
                sb.append(constraintViolation.getMessage());
            }

            throw new ConstraintViolationException("Error occurred: " + sb.toString(), violations);
        }
    }

}
