package com.example.facebookSpring.services.ServiceImplementation;

import com.example.facebookSpring.model.LikePost;
import com.example.facebookSpring.repository.LikePostRepository;
import com.example.facebookSpring.services.LikePostInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LikePostServiceImpl implements LikePostInterface {
    private final LikePostRepository likePostRepository;

    @Autowired
    public LikePostServiceImpl(LikePostRepository likePostRepository) {
        this.likePostRepository = likePostRepository;
    }

    public LikePost checkIfExists(Long postId){
        return likePostRepository.getLikePostByPostMessagePostId(postId);
    }
    public boolean addLikePost(LikePost likePost){
        likePostRepository.save(likePost);
        return true;
    }

    @Transactional
    public void deleteLikePost(Long postId){
        likePostRepository.deleteLikePostByPostMessagePostId(postId);
    }


}
