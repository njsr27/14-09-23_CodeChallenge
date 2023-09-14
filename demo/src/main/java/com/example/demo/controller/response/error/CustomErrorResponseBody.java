package com.example.demo.controller.response.error;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Value
@Builder
public class CustomErrorResponseBody {
    String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
    int status;
    String error;
    String message;
    String path;
}
