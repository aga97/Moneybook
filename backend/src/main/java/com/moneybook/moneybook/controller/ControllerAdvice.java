package com.moneybook.moneybook.controller;

import com.moneybook.moneybook.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;
import java.time.LocalDateTime;

@RestControllerAdvice
@CrossOrigin(origins = "*")
public class ControllerAdvice {

    @ExceptionHandler(DuplicatedMemberException.class)
    public ResponseEntity<ExceptionResponseBody> dupMember() {
        ExceptionResponseBody responseBody =
                new ExceptionResponseBody(LocalDateTime.now(), HttpStatus.BAD_REQUEST, "duplicated member");
        return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DuplicatedEmailException.class)
    public ResponseEntity<ExceptionResponseBody> dupEmail() {
        ExceptionResponseBody responseBody =
                new ExceptionResponseBody(LocalDateTime.now(), HttpStatus.BAD_REQUEST, "duplicated email");
        return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DuplicatedStockException.class)
    public ResponseEntity<ExceptionResponseBody> dupStock() {
        ExceptionResponseBody responseBody =
                new ExceptionResponseBody(LocalDateTime.now(), HttpStatus.BAD_REQUEST, "duplicated stock");
        return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidDateException.class)
    public ResponseEntity<ExceptionResponseBody> invalidDate() {
        ExceptionResponseBody responseBody =
                new ExceptionResponseBody(LocalDateTime.now(), HttpStatus.BAD_REQUEST, "invalid date");

        ResponseEntity<ExceptionResponseBody> test = new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
        System.out.println("test = " + test);

        return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
        //return new ExceptionResponseBody(LocalDateTime.now(), HttpStatus.BAD_REQUEST, "invalid date");
    }

    @ExceptionHandler(InvalidIdException.class)
    public ResponseEntity<ExceptionResponseBody> invalidId() {
        ExceptionResponseBody responseBody =
                new ExceptionResponseBody(LocalDateTime.now(), HttpStatus.BAD_REQUEST, "invalid id");
        return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidTickerException.class)
    public ResponseEntity<ExceptionResponseBody> invalidTicker() {
        ExceptionResponseBody responseBody =
                new ExceptionResponseBody(LocalDateTime.now(), HttpStatus.BAD_REQUEST, "invalid ticker");
        return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidUsernameException.class)
    public ResponseEntity<ExceptionResponseBody> invalidUsername() {
        ExceptionResponseBody responseBody =
                new ExceptionResponseBody(LocalDateTime.now(), HttpStatus.BAD_REQUEST, "invalid username");
        return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<ExceptionResponseBody> io() {
        ExceptionResponseBody responseBody =
                new ExceptionResponseBody(LocalDateTime.now(), HttpStatus.SERVICE_UNAVAILABLE, "yahoo finance io exception");
        return new ResponseEntity<>(responseBody, HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(InvalidStockQuantityException.class)
    public ResponseEntity<ExceptionResponseBody> invalidStockQuantity() {
        ExceptionResponseBody responseBody =
                new ExceptionResponseBody(LocalDateTime.now(), HttpStatus.BAD_REQUEST, "invalid stock quantity");
        return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
    }
}
