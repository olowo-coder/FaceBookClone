package com.example.facebookSpring.controller;

import com.example.facebookSpring.model.Comment;
import com.example.facebookSpring.model.PostMessage;
import com.example.facebookSpring.model.Users;
import com.example.facebookSpring.services.ServiceImplementation.CommentServiceImpl;
import com.example.facebookSpring.services.ServiceImplementation.LikePostServiceImpl;
import com.example.facebookSpring.services.ServiceImplementation.PostServiceImpl;
import com.example.facebookSpring.services.ServiceImplementation.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class UserController {

    private final UserServiceImpl userService;
    private final PostServiceImpl postService;
    private final CommentServiceImpl commentService;
    private final LikePostServiceImpl likePostService;

    @Autowired
    public UserController(UserServiceImpl userService, PostServiceImpl postService, CommentServiceImpl commentService, LikePostServiceImpl likePostService) {
        this.userService = userService;
        this.postService = postService;
        this.commentService = commentService;
        this.likePostService = likePostService;
    }

    @RequestMapping("/")
    public String indexPage(Model model) {
        Users user = new Users();
        model.addAttribute("user", user);
        return "index";
    }

    @GetMapping("/home")
    public String homepage(Model model, HttpServletRequest request){
        HttpSession session = request.getSession();
        Users user = (Users) session.getAttribute("user");
        if(user == null){
            session.setAttribute("error", "Please log In");
            return "redirect:/";
        }
        PostMessage newPost = new PostMessage();
        Comment newComment = new Comment();
        model.addAttribute("user", getUserData());
        model.addAttribute("postData", postService.getAllPost());
        model.addAttribute("commentData", commentService);
        model.addAttribute("newPostData", newPost);
        model.addAttribute("newComment", newComment);
        model.addAttribute("likePostData", likePostService);
        return "home";
    }

    @GetMapping("/signIn")
    public String index(Model model){
        Users user = new Users();
        model.addAttribute("user", user);
        return "index";
    }

    @PostMapping("/signIn")
    public String index(@ModelAttribute("user") Users user, Model model, HttpServletRequest request){
        String response = userService.validateUser(user);
        HttpSession session = request.getSession();
        if(response.equals("Not Registered Email")){
            model.addAttribute("error", response);
            setError(response);
            session.setAttribute("error", response);
            return "redirect:/";
        }
        else if(response.equals("Incorrect Password")){
            model.addAttribute("error", response);
            setError(response);
            session.setAttribute("error", response);
            return "redirect:/";
        }

        else {
            Optional<Users> user1 = userService.getUserByEmail(user.getEmail());
            session.setAttribute("user", user1.get());
            model.addAttribute("user", user1.get());
            setUserData(user1.get());
            return "redirect:/home";
        }
    }

    @GetMapping("/new")
    public String newAccount(Model model){
        Users user = new Users();
        model.addAttribute("user", user);
        return "create_account";
    }

    @PostMapping("/add")
    public String addUsers(@ModelAttribute("user") Users user, HttpServletRequest request){
        HttpSession session = request.getSession();
        boolean check = userService.addUser(user);
        if(check){
            session.setAttribute("error", "Email Taken");
            return "redirect:/new";
        }
        session.setAttribute("user", user);
        setUserData(user);
        return "welcome";
    }

    @GetMapping("/logOut")
    public String logOut(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.invalidate();
        return "redirect:/";
    }

    private String error;
    private Users userData;


    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Users getUserData() {
        return userData;
    }

    public void setUserData(Users userData) {
        this.userData = userData;
    }
}
