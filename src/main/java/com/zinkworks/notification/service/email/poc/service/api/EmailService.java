package com.zinkworks.notification.service.email.poc.service.api;

import com.zinkworks.notification.service.email.poc.model.EmailNotificationRequest;
import jakarta.mail.MessagingException;

import java.io.IOException;
import java.util.Map;

public interface EmailService {

    void sendPlaintextEmail(EmailNotificationRequest request);
    void sendHtmlMail(EmailNotificationRequest request, Map<String, Object> templateModel) throws MessagingException;

}
