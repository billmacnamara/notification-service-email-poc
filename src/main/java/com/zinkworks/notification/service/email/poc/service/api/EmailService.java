package com.zinkworks.notification.service.email.poc.service.api;

import com.zinkworks.notification.service.email.poc.model.EmailNotificationRequest;
import jakarta.mail.MessagingException;

public interface EmailService {

    void sendEmail(EmailNotificationRequest request);

    void sendEmailWithAttachment(EmailNotificationRequest request) throws MessagingException;

}
