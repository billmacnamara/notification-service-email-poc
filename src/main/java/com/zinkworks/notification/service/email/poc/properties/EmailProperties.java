package com.zinkworks.notification.service.email.poc.properties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString
@ConfigurationProperties(prefix = "email")
public class EmailProperties {

    private String mailServerHost;
    private Integer mailServerPort;
    private String mailServerUsername;
    private String mailServerPassword;
    private boolean debug;

}
