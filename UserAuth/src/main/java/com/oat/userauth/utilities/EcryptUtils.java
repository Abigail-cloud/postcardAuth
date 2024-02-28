package com.oat.userauth.utilities;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class EcryptUtils {
    private int strength = 10;

    public String encryptPassword(String password){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(strength);
        return bCryptPasswordEncoder.encode(password);
    }

    public boolean comparePassword(String rawPassword, String encodedpassword){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(strength);
        return bCryptPasswordEncoder.matches(rawPassword, encodedpassword);
    }
}
