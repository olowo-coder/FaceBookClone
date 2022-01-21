package com.example.facebookSpring.repository;

import com.example.facebookSpring.model.Comment;
import com.example.facebookSpring.model.LikePost;
import com.example.facebookSpring.model.PostMessage;
import com.example.facebookSpring.model.Users;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LikePostRepositoryTest {
    @Autowired
    private LikePostRepository likePostRepository;

    @Test
    void getLikePostByPostId() {
        LikePost likePost = likePostRepository.getLikePostByPostMessagePostId(51L);
        System.out.println(likePost);
    }

    @Test
    void deleteLikePostByPostMessagePostId() {
        Users user = Users.builder()
                .userId(1L)
                .firstName("John")
                .lastName("Samuel")
                .email("john@email.com")
                .phone("09034521345")
                .password("henry124")
                .build();
        PostMessage postMessage = PostMessage.builder()
                .postId(1L)
                .postBody("Checking and testing")
                .users(user)
                .timeStamp(LocalDateTime.now())
                .build();

    }
}