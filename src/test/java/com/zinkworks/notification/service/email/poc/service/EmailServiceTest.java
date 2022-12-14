package com.zinkworks.notification.service.email.poc.service;

import com.zinkworks.notification.service.email.poc.TestUtils;
import com.zinkworks.notification.service.email.poc.exception.MissingRecipientException;
import com.zinkworks.notification.service.email.poc.model.EmailNotificationRequest;
import com.zinkworks.notification.service.email.poc.service.impl.EmailServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;

import static org.junit.jupiter.api.Assertions.assertThrows;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
@EnableAutoConfiguration
public class EmailServiceTest {

    @Mock
    private JavaMailSender emailSender;

    @InjectMocks
    EmailServiceImpl emailService;

    @BeforeAll
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSendEmailSingleRecipient() {
        EmailNotificationRequest request =
                TestUtils.getPlaintextEmailNotificationRequest(new String[]{TestUtils.TEST_EMAIL1});
        this.emailService.sendPlaintextEmail(request);
    }

    @Test
    public void testSendEmailMultipleRecipients() {
        EmailNotificationRequest request =
                TestUtils.getPlaintextEmailNotificationRequest(new String[]{TestUtils.TEST_EMAIL1, TestUtils. TEST_EMAIL2});
        this.emailService.sendPlaintextEmail(request);
    }

    @Test
    public void testSendEmailNoRecipient() {
        EmailNotificationRequest request =
                TestUtils.getPlaintextEmailNotificationRequest(new String[]{});
        assertThrows(MissingRecipientException.class, () -> this.emailService.sendPlaintextEmail(request));
    }

}
