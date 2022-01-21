package com.example.facebookSpring.controller;

import com.example.facebookSpring.model.Comment;
import com.example.facebookSpring.model.PostMessage;
import com.example.facebookSpring.model.Users;
import com.example.facebookSpring.services.ServiceImplementation.CommentServiceImpl;
import com.example.facebookSpring.services.ServiceImplementation.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class CommentController {

    private final CommentServiceImpl commentService;
    private final PostServiceImpl postService;

    @Autowired
    public CommentController(CommentServiceImpl commentService, PostServiceImpl postService) {
        this.commentService = commentService;
        this.postService = postService;
    }


    @GetMapping("/home/comments/add/{postID}")
    public String saveComment(@ModelAttribute("comment") Comment comment, @PathVariable Long postID, HttpServletRequest request){
        HttpSession session = request.getSession();
        Users user = (Users) session.getAttribute("user");
        PostMessage postMessage = postService.getPostById(postID);
        comment.setPostMessage(postMessage);
        comment.setUsers(user);
        commentService.addNewComment(comment);
        return "redirect:/home";
    }

    @GetMapping("/home/comments/edit/{commentId}")
    public String editComment(@PathVariable Long commentId, Model model){
        model.addAttribute("comment", commentService.getCommentById(commentId));
        return "edit_mode";
    }

    @PostMapping("/home/comments/{commentId}")
    public String updateComment(@PathVariable Long commentId, @ModelAttribute("comment") Comment comment, Model model){
        Comment existingComment = commentService.getCommentById(commentId);
        existingComment.setCommentBody(comment.getCommentBody());
        existingComment.setCommentId(commentId);
//        model.addAttribute("comment", commentService.updateComment(comment));
        commentService.updateComment(comment);

        return "redirect:/home";
    }

    @GetMapping("/home/comments/{commentId}")
    public String removeComment(@PathVariable Long commentId){
        commentService.deleteComment(commentId);
        return "redirect:/home";
    }


}
