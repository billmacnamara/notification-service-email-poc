package com.zinkworks.notification.service.email.poc.model;

import lombok.Data;

@Data
public class EmailNotificationRequest {

    String[] to;
    EmailNotification emailNotification;

}
