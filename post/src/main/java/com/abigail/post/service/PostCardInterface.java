package com.abigail.post.service;

import com.example.postcard.dto.PostCardDto;
import com.example.postcard.entity.PostCardEntity;

import java.util.List;

public interface PostCardInterface {
 PostCardEntity createPostCard(PostCardDto obj);

 PostCardEntity getSinglePost( Long id);

 List<PostCardEntity> getAllPosts();

 PostCardEntity updateAllPosts(Long id, PostCardDto post);

 PostCardEntity deletePost(Long id);
}
