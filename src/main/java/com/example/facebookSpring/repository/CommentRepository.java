package com.example.facebookSpring.repository;

import com.example.facebookSpring.model.Comment;
import com.example.facebookSpring.model.PostMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
//    @Query("select s from Comment s where s.postMessage.postId = ?1")
//    List<Comment> findByPostMessageId(Long postId);
    List<Comment> findByPostMessagePostId(Long postId);

    int deleteAllByPostMessagePostId(Long postId);
}
