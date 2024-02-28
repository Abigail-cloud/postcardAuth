package com.oat.userauth.validations;

import com.oat.userauth.dto.UserDTO;
import org.springframework.stereotype.Component;


public interface UserValidationInterface {
    void clientValid(UserDTO user);
}
