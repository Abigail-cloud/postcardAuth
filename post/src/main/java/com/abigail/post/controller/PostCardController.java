package com.abigail.post.controller;

import com.example.postcard.dto.PostCardDto;
import com.example.postcard.entity.PostCardEntity;
import com.example.postcard.exceptions.PostNotFoundException;
import com.example.postcard.service.PostCardServiceimpl;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path = "/v1/api")
public class PostCardController {

    private final PostCardServiceimpl postCardServiceimpl;
    public PostCardController (PostCardServiceimpl postCardServiceimpl){
        this.postCardServiceimpl = postCardServiceimpl;
    }

    @GetMapping(value = "/posts")
    public HttpEntity<List<PostCardEntity>> getPosts () throws PostNotFoundException {
        List<PostCardEntity> getAllPosts = this.postCardServiceimpl.getAllPosts();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.ACCEPT, "Gotten");
        return new ResponseEntity<>(getAllPosts, httpHeaders, HttpStatus.OK);
    }

    @PostMapping(value = "/createPost")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR')")
    public HttpEntity<PostCardEntity>createPost(@RequestBody(required = false) PostCardDto post){
        PostCardEntity savePost = this.postCardServiceimpl.createPostCard(post);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.ACCEPT, "Created");
        return new ResponseEntity<>(savePost, headers, HttpStatus.CREATED);
    }
    @PutMapping(value = "/updatePosts/{id}")
    public HttpEntity<PostCardEntity> updatePosts (@PathVariable(value = "id") Long id, @RequestBody(required = false) PostCardDto data) throws PostNotFoundException {
        PostCardEntity updatePosts = this.postCardServiceimpl.updateAllPosts(id,data);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.ACCEPT, "Gotten");
        return new ResponseEntity<>(updatePosts, httpHeaders, HttpStatus.ACCEPTED);
    }
    @GetMapping(value = "/posts/{id}")
    public HttpEntity<PostCardEntity> getPostById (@PathVariable(value = "id") Long id) {
        PostCardEntity getAPost = this.postCardServiceimpl.getSinglePost(id);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.ACCEPT, "Gotten");
        return new ResponseEntity<>(getAPost, httpHeaders, HttpStatus.FOUND);
    }

    @DeleteMapping(value = "/deletePost/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public HttpEntity<PostCardEntity>deletePost(@PathVariable(value = "id") Long id){
        PostCardEntity deletePost = this.postCardServiceimpl.deletePost(id);
        String message = "The post is deleted";
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.ACCEPT, "Created");
        return new ResponseEntity<>(deletePost, headers, HttpStatus.CREATED);
    }
}
