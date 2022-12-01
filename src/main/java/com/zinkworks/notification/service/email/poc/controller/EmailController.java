package com.zinkworks.notification.service.email.poc.controller;

import com.zinkworks.notification.service.email.poc.model.EmailNotificationRequest;
import com.zinkworks.notification.service.email.poc.service.api.EmailService;
import com.zinkworks.notification.service.email.poc.service.impl.EmailServiceImpl;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import static com.zinkworks.notification.service.email.poc.constants.EmailServiceConstants.BASE_URL;

@RestController
@RequestMapping(path = BASE_URL)
@CommonsLog
@OpenAPIDefinition(info = @Info(title = "NDO Email Notification POC",
        version = "0.1.0",
        description = "NDO Email Notification API"))
@Tag(name = "Email Notification API")
public class EmailController {

    private final EmailService emailService;

    @Autowired
    EmailController(EmailServiceImpl emailService) {
        this.emailService = emailService;
    }

    @PostMapping
    @Operation(summary = "Send an email notification", description = "Send an email notification")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Email notification sent successfully"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "401", description = "Unauthorised"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Service error")
    })
    public ResponseEntity<EmailNotificationRequest> sendPlaintextMail(@Valid @RequestBody EmailNotificationRequest request) {
        this.emailService.sendPlaintextEmail(request);
        return new ResponseEntity<>(request, HttpStatus.OK);
    }

    @PostMapping(path = "/html")
    public ResponseEntity<EmailNotificationRequest> sendHtmlMail(@Valid @RequestBody EmailNotificationRequest request)
            throws MessagingException {
        Map<String, Object> templateModel = new HashMap<>();
        templateModel.put("message", request.getEmailNotification().getMessage());
        templateModel.put("notificationType", request.getEmailNotification().getNotificationType());
        templateModel.put("date", LocalDate.now());
        templateModel.put("time", LocalTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME));

        this.emailService.sendHtmlMail(request, templateModel);
        return new ResponseEntity<>(request, HttpStatus.OK);
    }

}
