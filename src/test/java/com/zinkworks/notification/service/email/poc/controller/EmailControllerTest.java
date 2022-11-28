package com.zinkworks.notification.service.email.poc.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.zinkworks.notification.service.email.poc.TestUtils;
import com.zinkworks.notification.service.email.poc.constants.EmailServiceConstants;
import com.zinkworks.notification.service.email.poc.model.EmailNotification;
import com.zinkworks.notification.service.email.poc.model.EmailNotificationRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc(printOnlyOnFailure = false)
@EnableAutoConfiguration
public class EmailControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testSendEmailSingleRecipient() throws Exception {
        EmailNotificationRequest request =
                TestUtils.getEmailNotificationRequest(new String[]{TestUtils.TEST_EMAIL1});
        MvcResult mvcResult = getEmailNotification(this.mockMvc, request);
        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
    }

    @Test
    public void testSendEmailMultipleRecipients() throws Exception {
        EmailNotificationRequest request =
                TestUtils.getEmailNotificationRequest(new String[]{TestUtils.TEST_EMAIL1, TestUtils. TEST_EMAIL2});
        MvcResult mvcResult = getEmailNotification(this.mockMvc, request);
        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
    }

    @Test
    public void testSendEmail_noRecipientError() throws Exception {
        EmailNotificationRequest request =
                TestUtils.getEmailNotificationRequest(new String[]{});
        MvcResult mvcResult = getEmailNotification(this.mockMvc, request);
        assertEquals(HttpStatus.BAD_REQUEST.value(), mvcResult.getResponse().getStatus());
    }

    private MvcResult getEmailNotification(final MockMvc mockMvc, final EmailNotificationRequest request) throws Exception {
        String url = EmailServiceConstants.BASE_URL;
        String requestJson = TestUtils.objectToJson(request);

        return mockMvc.perform(
                MockMvcRequestBuilders.post(url)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(requestJson)).andReturn();
    }
}
