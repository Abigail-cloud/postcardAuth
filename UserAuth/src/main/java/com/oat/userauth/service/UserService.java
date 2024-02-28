package com.oat.userauth.service;

import com.oat.userauth.dto.LoginDto;
import com.oat.userauth.dto.UserDTO;
import com.oat.userauth.entity.UserEntity;
import com.oat.userauth.exception.UserAlreadyExistException;
import com.oat.userauth.exception.UserNotFoundException;
import com.oat.userauth.repository.UserRepository;
import com.oat.userauth.utilities.EcryptUtils;
import com.oat.userauth.validations.UserValidation;
import com.oat.userauth.validations.UserValidationInterface;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserServiceInterface {
    private final UserRepository userRepository;
    private final UserValidationInterface userValidation;
    private EcryptUtils ecryptUtils;

    public UserService(UserRepository userRepository, UserValidationInterface userValidation, EcryptUtils ecryptUtils) {
        this.userRepository = userRepository;
        this.userValidation = userValidation;
        this.ecryptUtils = ecryptUtils;
    }

    public UserEntity createUser(UserDTO user) {
        userValidation.clientValid(user);
        String email = user.getEmail();
        String password = ecryptUtils.encryptPassword(user.getPassword());
        UserEntity savedUser = userRepository.findByEmail(email);
        if (savedUser != null) {
            throw new UserAlreadyExistException("User by " + email + " already exist");
        } else {
//            Do not return the user password
            UserEntity user1 = new UserEntity();
            user1.setEmail(email);
            user1.setPassword(password);
            user1.setName(user.getName());
            return userRepository.save(user1);
        }
    }
        public UserEntity logUser (LoginDto user){
            String email = user.getEmail();
            UserEntity logUser = userRepository.findByEmail(email);
            if (logUser == null) {
                throw new UserNotFoundException("User by " + email + " " + "does not exist" + "Kindly, Register");
            }
            String rawPassword = user.getPassword();
            String encodedPassword = logUser.getPassword();
            if (ecryptUtils.comparePassword(rawPassword, encodedPassword)) {
                return logUser;
            } else {
                throw new UserNotFoundException("Password incorrect!");
            }
        }
        public List<UserEntity> getAllUser () {
            return userRepository.findAll();
        }

    }

/**
 * Note just a thought on login.. not the standard
 * <p>
 * public UserEntity logUser(LoginDto user) {
 * String email = user.getEmail();
 * UserEntity logUser = userRepository.findByEmail(email);
 * if (logUser == null){
 * throw new UserNotFoundException("User by " + email + " " + "does not exist" + "Kindly, Register");
 * }
 * String rawPassword = user.getPassword();
 * String encodedPassword = logUser.getPassword();
 * if (encryptUtils.comparePassword(rawPassword, encodedPassword)){
 * return logUser;
 * }else {
 * throw new UserNotFoundException("Password incorrect!");
 * }
 * }
 */
