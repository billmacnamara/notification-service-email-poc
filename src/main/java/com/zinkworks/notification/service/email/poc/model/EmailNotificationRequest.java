package com.zinkworks.notification.service.email.poc.model;

import com.zinkworks.notification.service.email.poc.util.validation.ValidEmail;
import lombok.Data;

@Data
public class EmailNotificationRequest {

    @ValidEmail String[] to;
    EmailNotification emailNotification;

}
