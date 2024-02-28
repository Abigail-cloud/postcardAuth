package com.example.postcard.validations;

import com.example.postcard.dto.PostCardDto;

public interface CreatePostValidation {
    void postValidation(PostCardDto userName);
}
