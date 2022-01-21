package com.example.facebookSpring.services;

import com.example.facebookSpring.model.PostMessage;

import java.util.List;

public interface PostInterface {
    List<PostMessage> getAllPost();

    boolean addNewPost(PostMessage postMessage);

    PostMessage getPostById(Long postId);

    void updatePost(PostMessage postMessage);

    void deletePost(Long postId);
}
