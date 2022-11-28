package com.zinkworks.notification.service.email.poc.configuration;

import com.zinkworks.notification.service.email.poc.properties.EmailProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class EmailConfiguration {

    private final EmailProperties emailProperties;

    @Autowired
    EmailConfiguration(EmailProperties emailProperties) {
        this.emailProperties = emailProperties;
    }

    @Bean
    public JavaMailSender emailSender() {
        JavaMailSenderImpl emailSender = new JavaMailSenderImpl();
        emailSender.setHost(emailProperties.getMailServerHost());
        emailSender.setPort(emailProperties.getMailServerPort());
        emailSender.setUsername(emailProperties.getMailServerUsername());
        emailSender.setPassword(emailProperties.getMailServerPassword());

        Properties props = emailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", emailProperties.isDebug());

        return emailSender;
    }

}
