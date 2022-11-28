package com.zinkworks.notification.service.email.poc.controller;

import com.zinkworks.notification.service.email.poc.model.EmailNotificationRequest;
import com.zinkworks.notification.service.email.poc.service.api.EmailService;
import com.zinkworks.notification.service.email.poc.service.impl.EmailServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.mail.MessagingException;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/email")
@CommonsLog
public class EmailController {

    private final EmailService emailService;

    @Autowired
    EmailController(EmailServiceImpl emailService) {
        this.emailService = emailService;
    }

    @PostMapping
    @Operation(summary = "Send an email notification", description = "Send an email notification")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Email notification sent successfully")
    })
    public ResponseEntity<EmailNotificationRequest> sendMail(@RequestBody EmailNotificationRequest request) {
        if (request.getAttachment() != null) {
            try {
                this.emailService.sendEmailWithAttachment(request);
            } catch (MessagingException e) {
                log.error(e);
            }
        }

        this.emailService.sendEmail(request);
        return new ResponseEntity<>(request, HttpStatus.OK);
    }

}
