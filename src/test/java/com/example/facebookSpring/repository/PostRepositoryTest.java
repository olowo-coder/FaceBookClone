package com.example.facebookSpring.repository;

import com.example.facebookSpring.model.PostMessage;
import com.example.facebookSpring.model.Users;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class PostRepositoryTest {
    @Autowired
    private PostRepository postRepository;

    @Test
    public void addNewPost(){
        Users user = Users.builder()
                .userId(1L)
                .firstName("Henry")
                .lastName("Samuel")
                .email("henry@email.com")
                .phone("09034521345")
                .password("henry124")
                .build();
        PostMessage postMessage = PostMessage.builder()
                .postId(1L)
                .postBody("Checking and testing")
                .users(user)
                .timeStamp(LocalDateTime.now())
                .build();

        postRepository.save(postMessage);
    }

    @Test
    public void getAllPost() {
        List<PostMessage> postMessageList = postRepository.findAll();

        postMessageList.forEach(System.out::println);
    }

    @Test
    public void getPostById() {
        Users user = Users.builder()
                .userId(1L)
                .firstName("Henry")
                .lastName("Samuel")
                .email("henry@email.com")
                .phone("09034521345")
                .password("henry124")
                .build();
        PostMessage postMessage = PostMessage.builder()
                .postId(1L)
                .postBody("Checking and testing")
                .users(user)
                .timeStamp(LocalDateTime.now())
                .build();

        Optional<PostMessage> postMessage1 = postRepository.findById(postMessage.getPostId());
        if (postMessage1.isPresent()){
            System.out.println("Yes");
        }
        else {
            System.out.println("no");
        }
    }



}