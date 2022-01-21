package com.example.facebookSpring.repository;

import com.example.facebookSpring.model.Comment;
import com.example.facebookSpring.model.LikePost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikePostRepository extends JpaRepository<LikePost, Long> {
    LikePost getLikePostByPostMessagePostId (Long postId);

    void deleteLikePostByPostMessagePostId(Long postId);
}
