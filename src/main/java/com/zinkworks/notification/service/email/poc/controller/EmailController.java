package com.zinkworks.notification.service.email.poc.controller;

import com.zinkworks.notification.service.email.poc.model.EmailNotificationRequest;
import com.zinkworks.notification.service.email.poc.service.api.EmailService;
import com.zinkworks.notification.service.email.poc.service.impl.EmailServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.zinkworks.notification.service.email.poc.constants.EmailServiceConstants.BASE_URL;

@RestController
@RequestMapping(path = BASE_URL)
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
    public ResponseEntity<EmailNotificationRequest> sendMail(@Valid @RequestBody EmailNotificationRequest request) {
        this.emailService.sendEmail(request);
        return new ResponseEntity<>(request, HttpStatus.OK);
    }

}
