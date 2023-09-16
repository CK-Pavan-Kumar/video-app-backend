package com.videoappbackend.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class Exception {
    @ExceptionHandler(RuntimeException.class)

    public ResponseEntity<?> exception(RuntimeException obj){
        return new ResponseEntity<>(obj.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
