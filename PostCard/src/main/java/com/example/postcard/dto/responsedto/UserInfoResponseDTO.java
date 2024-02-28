package com.example.postcard.dto.responsedto;

import java.util.List;

public class UserInfoResponseDTO {
    private Long id;
    private String name;
    private String email;
    private List<String> roles;

    public UserInfoResponseDTO( String name, String email,Long id, List<String> roles){
        this.email = email;
        this.name = name;
        this.id = id;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
