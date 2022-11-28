package com.zinkworks.notification.service.email.poc.controller;

import com.zinkworks.notification.service.email.poc.model.EmailNotification;
import com.zinkworks.notification.service.email.poc.model.EmailNotificationRequest;
import com.zinkworks.notification.service.email.poc.service.impl.EmailServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc(printOnlyOnFailure = false)
@EnableAutoConfiguration
public class EmailControllerTest {

    @Mock
    EmailServiceImpl emailService;

    @InjectMocks
    EmailController emailController;

    @Test
    public void testSendEmailSingleRecipient() {
        EmailNotificationRequest request = new EmailNotificationRequest();
        request.setTo(new String[]{"testuser123@zinkworks.com"});
        EmailNotification notification = new EmailNotification();
        notification.setSubject("Test");
        notification.setBody("This is a test email");
        request.setEmailNotification(notification);

//        Mockito.when()
    }
}
