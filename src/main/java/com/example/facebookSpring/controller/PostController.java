package com.example.facebookSpring.controller;

import com.example.facebookSpring.model.Comment;
import com.example.facebookSpring.model.PostMessage;
import com.example.facebookSpring.model.Users;
import com.example.facebookSpring.services.ServiceImplementation.CommentServiceImpl;
import com.example.facebookSpring.services.ServiceImplementation.LikePostServiceImpl;
import com.example.facebookSpring.services.ServiceImplementation.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

@Controller
public class PostController {
    private final PostServiceImpl postService;
    private final CommentServiceImpl commentService;
    private final LikePostServiceImpl likePostService;


    @Autowired
    public PostController(PostServiceImpl postService, CommentServiceImpl commentService, LikePostServiceImpl likePostService) {
        this.postService = postService;
        this.commentService = commentService;
        this.likePostService = likePostService;
    }


    @PostMapping("/home/pmessage")
    public String addPost(@ModelAttribute("postMessage") PostMessage postMessage, HttpServletRequest request){
        HttpSession session = request.getSession();
        Users user = (Users) session.getAttribute("user");
        postMessage.setUsers(user);
        postMessage.setTimeStamp(LocalDateTime.now());
        postService.addNewPost(postMessage);
        return "redirect:/home";
    }

    @GetMapping("/home/pmessage/edit/{postId}")
    public String editComment(@PathVariable Long postId, Model model, HttpServletRequest request){
        PostMessage newPost = new PostMessage();
        Comment newComment = new Comment();
        HttpSession session = request.getSession();
        Users user = (Users) session.getAttribute("user");
        if(user == null){
            session.setAttribute("error", "Please log In");
            return "redirect:/";
        }

        model.addAttribute("user", user);
        model.addAttribute("modePostData", postService.getAllPost());
        model.addAttribute("modeComments", commentService.getAllComment());
        model.addAttribute("newPostData", newPost);
        model.addAttribute("newComment", newComment);
        model.addAttribute("postData", postService.getPostById(postId));
        model.addAttribute("likePostData", likePostService);
        return "edit_mode";
    }

    @PostMapping("/home/pmessage/{postId}")
    public String updatePost(@PathVariable Long postId, @ModelAttribute("postData") PostMessage postMessage, Model model){
        PostMessage existingPost = postService.getPostById(postId);
        existingPost.setPostBody(postMessage.getPostBody());
        existingPost.setPostId(postMessage.getPostId());
        postService.updatePost(existingPost);
        return "redirect:/home";
    }

    @GetMapping("/home/pmessage/{postId}")
    public String removeComment(@PathVariable Long postId){
        likePostService.deleteLikePost(postId);
        commentService.deleteAllByPostId(postId);
        postService.deletePost(postId);
        return "redirect:/home";
    }

}
