package com.zinkworks.notification.service.email.poc.model;

import lombok.Data;

import java.io.File;

@Data
public class EmailAttachment {
    String fileName;
    File file;
    String filePath;
}
