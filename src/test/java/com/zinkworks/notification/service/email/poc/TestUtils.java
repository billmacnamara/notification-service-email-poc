package com.zinkworks.notification.service.email.poc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.zinkworks.notification.service.email.poc.model.EmailNotification;
import com.zinkworks.notification.service.email.poc.model.EmailNotificationRequest;

public class TestUtils {

    public static final String TEST_EMAIL1 = "testuser123@zinkworks.com";
    public static final String TEST_EMAIL2 = "testuserABC@zinkworks.com";
    public static final String INVALID_EMAIL = "testuserABCzinkworkscom";

    public static EmailNotificationRequest getPlaintextEmailNotificationRequest(String[] to) {
        EmailNotificationRequest request = new EmailNotificationRequest();
        request.setTo(to);
        EmailNotification notification = new EmailNotification();
        notification.setSubject("Test");
        notification.setMessage("This is a test email");
        request.setEmailNotification(notification);

        return request;
    }

    public static EmailNotificationRequest getHtmlEmailNotificationRequest(String[] to) {
        EmailNotificationRequest request = new EmailNotificationRequest();
        request.setTo(to);
        EmailNotification notification = new EmailNotification();
        notification.setSubject("Test");
        notification.setMessage("This is a test email");
        notification.setNotificationType("INFO");
        request.setEmailNotification(notification);

        return request;
    }

    public static String objectToJson(Object o) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter writer = objectMapper.writer().withDefaultPrettyPrinter();
        return writer.writeValueAsString(o);
    }

}
