package com.zinkworks.notification.service.email.poc.model;

import lombok.Data;

import java.io.File;

@Data
public class EmailNotificationRequest {

    String[] to;
    EmailNotification emailNotification;
    EmailAttachment attachment;

}
