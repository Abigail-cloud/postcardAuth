package com.abigail.post.controller;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(value = "*", maxAge = 3600)
@RestController
@RequestMapping("v1/api")
public class TestController {

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public String allAccessRoute(){
        return "Admin Access";
    }
}
