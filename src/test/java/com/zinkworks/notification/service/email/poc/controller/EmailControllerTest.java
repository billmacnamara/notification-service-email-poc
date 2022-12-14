package com.zinkworks.notification.service.email.poc.controller;

import com.zinkworks.notification.service.email.poc.TestUtils;
import com.zinkworks.notification.service.email.poc.constants.EmailServiceConstants;
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
                TestUtils.getPlaintextEmailNotificationRequest(new String[]{TestUtils.TEST_EMAIL1});
        MvcResult mvcResult = getEmailNotification(this.mockMvc, request);
        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
    }

    @Test
    public void testSendPlaintextEmailMultipleRecipients() throws Exception {
        EmailNotificationRequest request =
                TestUtils.getPlaintextEmailNotificationRequest(new String[]{TestUtils.TEST_EMAIL1, TestUtils. TEST_EMAIL2});
        MvcResult mvcResult = getEmailNotification(this.mockMvc, request);
        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
    }

    @Test
    public void testSendHtmlEmailSingleRecipient() throws Exception {
        EmailNotificationRequest request =
                TestUtils.getHtmlEmailNotificationRequest(new String[]{TestUtils.TEST_EMAIL1});
        MvcResult mvcResult = getHtmlEmailNotification(this.mockMvc, request);
        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
    }

    @Test
    public void testSendHtmlEmailMultipleRecipients() throws Exception {
        EmailNotificationRequest request =
                TestUtils.getHtmlEmailNotificationRequest(new String[]{TestUtils.TEST_EMAIL1, TestUtils. TEST_EMAIL2});
        MvcResult mvcResult = getHtmlEmailNotification(this.mockMvc, request);
        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
    }

    @Test
    public void testSendEmail_noRecipientError() throws Exception {
        EmailNotificationRequest request =
                TestUtils.getPlaintextEmailNotificationRequest(new String[]{});
        MvcResult mvcResult = getEmailNotification(this.mockMvc, request);
        assertEquals(HttpStatus.BAD_REQUEST.value(), mvcResult.getResponse().getStatus());
    }

//    @Test
//    public void testSendEmail_InvalidEmailAddressError() throws Exception {
        // TODO -> behaviour when an invalid address is supplied still needs TBD
//
//        EmailNotificationRequest request =
//                TestUtils.getEmailNotificationRequest(new String[]{TestUtils.INVALID_EMAIL});
//        MvcResult mvcResult = getEmailNotification(this.mockMvc, request);
//        assertEquals(HttpStatus.ACCEPTED.value(), mvcResult.getResponse().getStatus());
//    }

    private MvcResult getEmailNotification(final MockMvc mockMvc, final EmailNotificationRequest request) throws Exception {
        String url = EmailServiceConstants.BASE_URL;
        String requestJson = TestUtils.objectToJson(request);

        return mockMvc.perform(
                MockMvcRequestBuilders.post(url)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(requestJson)).andReturn();
    }

    private MvcResult getHtmlEmailNotification(final MockMvc mockMvc, final EmailNotificationRequest request) throws Exception {
        String url = EmailServiceConstants.BASE_URL + "/html";
        String requestJson = TestUtils.objectToJson(request);

        return mockMvc.perform(
                MockMvcRequestBuilders.post(url)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(requestJson)).andReturn();
    }
}
