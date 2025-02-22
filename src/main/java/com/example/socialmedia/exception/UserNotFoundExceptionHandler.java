package com.example.socialmedia.exception;


import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.socialmedia.user.UserNotFoundException;

@ControllerAdvice
public class UserNotFoundExceptionHandler extends ResponseEntityExceptionHandler{

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorDetails> handleAllExceptions(Exception ex, WebRequest webRequest){
        ErrorDetails errorDetails = new ErrorDetails(ex.getMessage(), webRequest.getDescription(false), LocalDateTime.now());
        return new ResponseEntity<ErrorDetails>(errorDetails,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<ErrorDetails> handleUserNotFoundException(UserNotFoundException uex, WebRequest webRequest){
        ErrorDetails errorDetails = new ErrorDetails(uex.getMessage(), webRequest.getDescription(false), LocalDateTime.now());
        return new ResponseEntity<ErrorDetails>(errorDetails,HttpStatus.NOT_FOUND);
    }


}

