package com.example.facebookSpring.services;

import com.example.facebookSpring.model.Comment;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CommentInterface {

    List<Comment> getAllComment();

    boolean addNewComment(Comment comment);

    void deleteComment(Long commentId);

    @Transactional
    void updateComment(Comment comment);
}
