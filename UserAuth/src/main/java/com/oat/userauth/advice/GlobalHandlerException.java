package com.oat.userauth.advice;

import com.oat.userauth.dto.UserErrorDTO;
import com.oat.userauth.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
//Intercept exception

@ControllerAdvice
public class GlobalHandlerException extends ResponseEntityExceptionHandler {
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?>userNotFoundException(UserNotFoundException ex, WebRequest request){
        UserErrorDTO userErrorDTO = new UserErrorDTO(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(userErrorDTO, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?>globalExceptionHandler(Exception ex, WebRequest request){
        UserErrorDTO errorDetails = new UserErrorDTO(new Date(),  ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
