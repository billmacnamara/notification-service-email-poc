package com.zinkworks.notification.service.email.poc.model;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class EmailNotification {

    private String subject;
    private String message;
    private String notificationType;

}
