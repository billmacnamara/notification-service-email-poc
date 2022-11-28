package com.zinkworks.notification.service.email.poc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class NotificationServiceEmailPocApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotificationServiceEmailPocApplication.class, args);
	}

}
