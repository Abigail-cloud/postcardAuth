package com.abigail.post.dto.request;

import jakarta.validation.constraints.NotBlank;

public class LoginDto {
    @NotBlank
    private String email;
    @NotBlank
    private String password;

    public String getEmail(){
        return this.email;
    }
    public void setEmail(String email){
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
