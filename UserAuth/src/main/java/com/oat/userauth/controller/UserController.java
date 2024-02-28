package com.oat.userauth.controller;

import com.oat.userauth.dto.LoginDto;
import com.oat.userauth.dto.UserDTO;
import com.oat.userauth.entity.UserEntity;
import com.oat.userauth.service.UserService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/v1/api")
public class UserController {
    private UserService userService;
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/getUser")
  public String getUser(){
        return "Users";
    }

    @PostMapping(value = "/register")
    HttpEntity<UserEntity> register(@RequestBody(required = false) UserDTO user){
        UserEntity registerUser = this.userService.createUser(user);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.ACCEPT, "Created");
        return new ResponseEntity<>(registerUser, headers, HttpStatus.CREATED);

    }

    @PostMapping("/login")
    HttpEntity<UserEntity> logUser(@RequestBody(required = false) LoginDto user){
        UserEntity login = (UserEntity) this.userService.logUser(user);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.ACCEPT, "User Accepted");
        return new ResponseEntity<>(login, headers, HttpStatus.ACCEPTED);
    }

    @GetMapping("/getAllUsers")
    HttpEntity<List<UserEntity>> getAllUser(){
        List<UserEntity>getUsers = this.userService.getAllUser();
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.ACCEPT, "Get all");
        return new ResponseEntity<>(getUsers, headers, HttpStatus.FOUND);
    }
}
