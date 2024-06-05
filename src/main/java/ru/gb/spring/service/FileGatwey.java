package ru.gb.spring.service;

import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.file.FileHeaders;
import org.springframework.messaging.handler.annotation.Header;

;
@MessagingGateway(defaultRequestChannel = "textInputChannel")
public class FileGatwey {
    void writeFile(@Header(FileHeaders.FILENAME) String filename, String data) {

    }
}
