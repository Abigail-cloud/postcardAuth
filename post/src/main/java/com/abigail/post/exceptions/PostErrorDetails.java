package com.abigail.post.exceptions;

import java.util.Date;

public class PostErrorDetails {
    private Date timeStamps;
    private String message;
    private String details;

    public PostErrorDetails(Date timeStamps, String message, String details){
        super();
        this.timeStamps = timeStamps;
        this.message = message;
        this.details = details;
    }
    public Date getTimeStamps(){
        return this.timeStamps;
    }
    public String getMessage(){
        return this.message;
    }
    public String getDetails(){
        return this.details;
    }
}
