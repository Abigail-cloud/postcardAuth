package com.example.postcard.service;

import com.example.postcard.dto.PostCardDto;
import com.example.postcard.entity.PostCardEntity;
import com.example.postcard.exceptions.PostNotFoundException;
import com.example.postcard.repository.PostCardRepository;
import com.example.postcard.validations.Validation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostCardServiceimpl implements PostCardInterface{

    private final PostCardRepository postCardRepository;
    private final Validation validation;

    private PostCardServiceimpl(PostCardRepository postCardRepository, Validation validation){
        this.postCardRepository = postCardRepository;
        this.validation = validation;
    }


    @Override
    public PostCardEntity createPostCard(PostCardDto obj) {
        validation.postValidation(obj);
        PostCardEntity posts = new PostCardEntity();
        BeanUtils.copyProperties(obj, posts);
        return postCardRepository.save(posts);
    }
    @Override
    public List<PostCardEntity> getAllPosts() {
        return postCardRepository.findAll();
    }
    @Override
    public PostCardEntity getSinglePost(Long id) throws PostNotFoundException {
        Optional<PostCardEntity> posts = this.postCardRepository.findById(id);
//        return posts.orElse(null);
        if (posts.isPresent()){
            return posts.get();
        }
        return null;
    }

    @Override
    public PostCardEntity updateAllPosts(Long id, PostCardDto post) {
        Optional<PostCardEntity> updatePost = this.postCardRepository.findById(id);
        if (updatePost.isPresent()){
            PostCardEntity postCard = updatePost.get();
            postCard.setPosts(post.getPosts());
            postCard.setUserName(post.getUserName());
            postCardRepository.save(postCard);
            return postCard;
        }
        return null;
    }

    @Override
    public PostCardEntity deletePost(Long id) throws PostNotFoundException {
        Optional<PostCardEntity> post = this.postCardRepository.findById(id);
        if (post.isPresent()){
            this.postCardRepository.delete(post.get());
        }
        return null;
    }
}
