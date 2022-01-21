package com.example.facebookSpring.services;

import com.example.facebookSpring.model.LikePost;

public interface LikePostInterface {

    LikePost checkIfExists(Long postId);

    boolean addLikePost(LikePost likePost);

    void deleteLikePost(Long likePostId);
}
