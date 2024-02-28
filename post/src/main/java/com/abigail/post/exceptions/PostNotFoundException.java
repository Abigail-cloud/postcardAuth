package com.abigail.post.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class PostNotFoundException extends RuntimeException{
    private static final Long serialVersionUID = 1L;
    public PostNotFoundException(String message){
        super(message);
    }

}
