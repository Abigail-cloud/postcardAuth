package com.example.postcard.dto;


public class PostCardDto {

    private String posts;
    private String userName;

    public String getPosts(){
       return this.posts;
    }
    public void setPosts(String posts){
        this.posts = posts;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserNAme(String userName) {
        this.userName = userName;
    }
}
