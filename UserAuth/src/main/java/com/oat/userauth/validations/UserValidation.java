package com.oat.userauth.validations;

import com.oat.userauth.dto.UserDTO;
import com.oat.userauth.exception.UserNotFoundException;
import org.springframework.stereotype.Component;


@Component
public class UserValidation implements UserValidationInterface {

    private void isUserValid(UserDTO user) {
        String email = user.getEmail();
        if (email == null || email.endsWith("o") || email.isEmpty()) {
            throw new UserNotFoundException("The " + email + "is not valid");
        }
    }

    private void isPasswordValid(UserDTO user) {
        String password = user.getPassword();
        if (password == null || password.isEmpty() || password.length() <= 3) {
            throw new UserNotFoundException("Enter a valid password");
        }
    }

    public void clientValid(UserDTO user) {
        isUserValid(user);
        isPasswordValid(user);
    }
}
