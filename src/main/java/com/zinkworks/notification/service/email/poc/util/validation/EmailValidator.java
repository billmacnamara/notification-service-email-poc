package com.zinkworks.notification.service.email.poc.util.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.apachecommons.CommonsLog;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@CommonsLog
public class EmailValidator implements ConstraintValidator<ValidEmail, String[]> {
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    @Override
    public void initialize(final ValidEmail constraintAnnotation) {
    }

    @Override
    public boolean isValid(String[] emails, ConstraintValidatorContext context) {
        for (String email : emails) {
            boolean valid = validateEmail(email);
            if (!valid) {
                log.warn("Invalid email address was supplied: " + email);
            }
        }
        return true;
    }

    private boolean validateEmail(final String emailAddress) {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(emailAddress);
        return matcher.matches();
    }

}
