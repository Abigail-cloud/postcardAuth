package com.oat.userauth.dto;

import java.util.Date;

public class UserErrorDTO {
    private Date timeStamps;
    private String message;
    private String description;

    public UserErrorDTO(Date timeStamps, String message, String description){
        super();
        this.timeStamps = timeStamps;
        this.message = message;
        this.description = description;
    }

    public String getMessage(){
        return this.message;
    }

    public String getDescription(){
        return this.description;
    }

    public Date getTimeStamps(){
        return this.timeStamps;
    }
}
