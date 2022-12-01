package com.zinkworks.notification.service.email.poc.service.impl;

import com.zinkworks.notification.service.email.poc.constants.EmailServiceConstants;
import com.zinkworks.notification.service.email.poc.exception.MissingRecipientException;
import com.zinkworks.notification.service.email.poc.model.EmailNotificationRequest;
import com.zinkworks.notification.service.email.poc.service.api.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import java.nio.charset.StandardCharsets;
import java.util.Map;

@Service
@CommonsLog
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender emailSender;

    @Autowired
    private SpringTemplateEngine springThymeleafTemplateEngine;

    @Autowired
    EmailServiceImpl(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    @Override
    public void sendPlaintextEmail(EmailNotificationRequest request) {
        if (request.getTo() == null || request.getTo().length == 0 ) {
            throw new MissingRecipientException("No email recipient was provided");
        }

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(EmailServiceConstants.SENDING_ADDRESS);
        message.setTo(request.getTo());

        message.setSubject(request.getEmailNotification().getSubject());
        message.setText(request.getEmailNotification().getMessage());

        emailSender.send(message);
    }

    public void sendHtmlMail(EmailNotificationRequest request, Map<String, Object> templateModel) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, StandardCharsets.UTF_8.name());

        Context context = new Context();
        context.setVariables(templateModel);

        helper.setFrom(EmailServiceConstants.SENDING_ADDRESS);
        helper.setTo(request.getTo());
        helper.setSubject(request.getEmailNotification().getSubject());
        String htmlBody = springThymeleafTemplateEngine.process(EmailServiceConstants.EMAIL_TEMPLATE, context);
        helper.setText(htmlBody, true);

        emailSender.send(message);
    }

}
