package com.datingfood.backend.exception;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(PersonNotFoundException.class)
    public String exceptionHandler(PersonNotFoundException unfe){
        return "UserNotFoundException:"+unfe.getMessage();
    }
}
