package com.example.facebookSpring.controller;

import com.example.facebookSpring.model.LikePost;
import com.example.facebookSpring.model.PostMessage;
import com.example.facebookSpring.model.Users;
import com.example.facebookSpring.services.ServiceImplementation.LikePostServiceImpl;
import com.example.facebookSpring.services.ServiceImplementation.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

@Controller
public class LikePostController {
    private final LikePostServiceImpl likePostService;
    private final PostServiceImpl postService;

    @Autowired
    public LikePostController(LikePostServiceImpl likePostService, PostServiceImpl postService) {
        this.likePostService = likePostService;
        this.postService = postService;
    }

    @GetMapping("/home/likePost/{postId}")
    public String addPost(@ModelAttribute("likePostData")LikePost likePost, @PathVariable Long postId, HttpServletRequest request){
        HttpSession session = request.getSession();
        Users user = (Users) session.getAttribute("user");
        PostMessage postMessage = postService.getPostById(postId);
        LikePost likePost1 = likePostService.checkIfExists(postId);

        if(likePost1 == null){
            likePost.setPLike(1L);
            likePost.setPostMessage(postMessage);
            likePostService.addLikePost(likePost);
        }
        else {
            likePostService.deleteLikePost(postId);
        }
        return "redirect:/home";
    }


}
