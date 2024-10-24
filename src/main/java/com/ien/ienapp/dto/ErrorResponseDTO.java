package com.ien.ienapp.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponseDTO {
    
    private String message;
    private int statusCode;
    private LocalDateTime timestamp;
    private String details;


    public ErrorResponseDTO(String message, int statusCode, String details) {
        this.message = message;
        this.statusCode = statusCode;
        this.timestamp = LocalDateTime.now();
        this.details = details;
    }
    public ErrorResponseDTO() {}
}
