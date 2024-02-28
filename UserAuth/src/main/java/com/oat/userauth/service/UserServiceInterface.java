package com.oat.userauth.service;

import com.oat.userauth.dto.LoginDto;
import com.oat.userauth.dto.UserDTO;
import com.oat.userauth.entity.UserEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserServiceInterface {
    UserEntity createUser(UserDTO user);

    UserEntity logUser(LoginDto user);

    List<UserEntity> getAllUser();
}
