package com.example.postcard.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.method.MethodValidationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class GlobalHandlerException extends ResponseEntityExceptionHandler {
    @ExceptionHandler(PostNotFoundException.class)
    public ResponseEntity<?>postNotFoundException(PostNotFoundException except, WebRequest request){
        PostErrorDetails postErrorDetails = new PostErrorDetails(new Date(), except.getMessage(), request.getDescription(false));
        return  new ResponseEntity<>(postErrorDetails, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?>globeExceptionHandler(Exception except, WebRequest request){
        PostErrorDetails errorDetails = new PostErrorDetails(new Date(), except.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @Override
    protected ResponseEntity<Object>handleMethodValidationException(MethodValidationException except, HttpHeaders headers,
                                                                    HttpStatus status, WebRequest request){
        PostErrorDetails errorDetails = new PostErrorDetails(new Date(), "Validation Failed",
                except.getAllValidationResults().toString());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
    // except.getBindingResult().toString());
}
