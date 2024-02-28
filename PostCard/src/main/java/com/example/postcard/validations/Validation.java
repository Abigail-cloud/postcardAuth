package com.example.postcard.validations;


import com.example.postcard.dto.PostCardDto;
import com.example.postcard.exceptions.PostNotFoundException;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class Validation implements CreatePostValidation {
    @Override
    public void postValidation(PostCardDto userName) {
//        String a = null;
//        String b = "";
//        a.length();
//        b.length();
    String name = userName.getUserName();
    if (name == null || name.isEmpty()){
       throw new PostNotFoundException("Input a name as the author");
    }
    }
}
