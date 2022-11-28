package com.zinkworks.notification.service.email.poc.service.api;

import com.zinkworks.notification.service.email.poc.model.EmailNotificationRequest;

public interface EmailService {

    void sendEmail(EmailNotificationRequest request);

}
