package com.zinkworks.notification.service.email.poc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "No email recipient provided")
public class MissingRecipientException extends RuntimeException {

    public MissingRecipientException(String msg) {
        super(msg);
    }

}
