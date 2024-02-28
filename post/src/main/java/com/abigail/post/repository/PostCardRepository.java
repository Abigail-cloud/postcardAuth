package com.abigail.post.repository;

import com.example.postcard.entity.PostCardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostCardRepository extends JpaRepository<PostCardEntity, Long> {
}
