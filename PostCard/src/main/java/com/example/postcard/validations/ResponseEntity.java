package com.example.postcard.validations;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatusCode;
import org.springframework.lang.Nullable;
//
//public class ResponseValidation {
//    public void ResponseEntity(@Nullable T body, @Nullable MultiValueMap<String, String> headers, HttpStatusCode statusCode, String message) {
//        super();
//        Assert.notNull(statusCode, "HttpStatusCode must not be null");
//        this.status = statusCode;
//        message = "This post no longer exist!!";
//    }
//}

public class ResponseEntity<T> extends HttpEntity<T> {

    private String message;
    private HttpStatusCode status;
    public ResponseEntity(T body, Object status, HttpStatusCode status1, String message, String message1) {
        this.message = message1;
    }

    public ResponseEntity(T body, Object o, HttpStatusCode status, String message) {
        this.message = message;
    }
}
