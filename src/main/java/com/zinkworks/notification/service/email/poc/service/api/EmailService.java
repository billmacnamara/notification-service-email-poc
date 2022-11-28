package com.zinkworks.notification.service.email.poc.service.api;

import com.zinkworks.notification.service.email.poc.model.EmailNotification;
import com.zinkworks.notification.service.email.poc.model.EmailNotificationRequest;
import jakarta.mail.MessagingException;

public interface EmailService {

    public void sendEmail(EmailNotificationRequest request);
    public void sendEmailWithAttachment(EmailNotificationRequest request) throws MessagingException;

}
