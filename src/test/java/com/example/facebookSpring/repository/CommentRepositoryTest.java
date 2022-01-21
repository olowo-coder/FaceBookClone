package com.example.facebookSpring.repository;

import com.example.facebookSpring.model.Comment;
import com.example.facebookSpring.model.PostMessage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;

@SpringBootTest
class CommentRepositoryTest {
    @Autowired
    private CommentRepository commentRepository;

    @Test
    public void getAllComment() {
        List<Comment> commentList = commentRepository.findAll();
//        for(Comment com : commentList()){
//            com.
//        }

        commentList.forEach(System.out::println);
    }


    @Test
    public void getAllCommentById() {
        List<Comment> commentList = commentRepository.findByPostMessagePostId(50L);
        commentList.forEach(System.out::println);
    }
}
