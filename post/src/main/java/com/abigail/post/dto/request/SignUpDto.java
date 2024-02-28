package com.abigail.post.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Set;

public class SignUpDto {
    @NotBlank
    @Size(min = 3, max = 10)
    private String name;
    @NotBlank
    @Size(min = 4)
    private String email;
    @NotBlank
    @Size(min = 4)
    private String password;
    private Set<String> role;

    public Set<String> getRole() {
        return role;
    }

    public void setRole(Set<String> role) {
        this.role = role;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
