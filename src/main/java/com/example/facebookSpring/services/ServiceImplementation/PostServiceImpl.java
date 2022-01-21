package com.example.facebookSpring.services.ServiceImplementation;

import com.example.facebookSpring.model.PostMessage;
import com.example.facebookSpring.model.Users;
import com.example.facebookSpring.repository.PostRepository;
import com.example.facebookSpring.repository.UserRepository;
import com.example.facebookSpring.services.PostInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PostServiceImpl implements PostInterface {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public List<PostMessage> getAllPost(){
        return postRepository.findAll();
//        return postRepository.findAll(Sort.by(Sort.Direction.DESC, "postId"));
    }

    public boolean addNewPost(PostMessage postMessage){
        Users user = userRepository.findById(postMessage.getUsers().getUserId()).get();
        if(user != null) {
            postRepository.save(postMessage);
            return true;
        }
        else {
            return false;
        }
    }

    public PostMessage getPostById(Long postId){
        return postRepository.findById(postId).get();
    }

    @Transactional
    public void updatePost(PostMessage postMessage){
        PostMessage postMessage1 = postRepository.findById(postMessage.getPostId()).orElseThrow(() -> new IllegalStateException("does not exist"));
        postMessage1.setPostBody(postMessage.getPostBody());
    }

    @Transactional
    public void deletePost(Long postId) {
        boolean exist = postRepository.existsById(postId);
        if(!exist){
            throw  new IllegalStateException("Post with id -> " + postId + " does not exist");
        }
        postRepository.deleteById(postId);
    }

}
