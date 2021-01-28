package com.moneybook.moneybook.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class ExceptionResponseBody {
    private LocalDateTime timestamp;
    private HttpStatus status;
    private String message;

    public ExceptionResponseBody(LocalDateTime timestamp, HttpStatus status, String message) {
        this.timestamp = timestamp;
        this.status = status;
        this.message = message;
    }
}
