package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.models.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {

    // ===== Post Exercise =====
//    return strings describe what will ultimately be returned
//    method	url	                description
//    GET	    /posts	            posts index page
//    GET	    /posts/{id}	        view an individual post
//    GET	    /posts/create	    view the form for creating a post
//    POST	    /posts/create	    create a new post

    @GetMapping("/posts")
    public String getPosts(Model model){
        List<Post> posts = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            Post post = new Post("Dog for sale!", "Good Boy. 1 years old. Moving to new place and can not take him with me.");
            posts.add(post);
        }
        model.addAttribute("posts", posts);
        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String getPosts(@PathVariable long id, Model model){
        Post post = new Post("Dog for sale!", "Good Boy. 1 years old. Moving to new place and can not take him with me.");
        model.addAttribute("post", post);
        return "posts/show";
    }

    @GetMapping("/posts/create")
    @ResponseBody
    public String createPostView(){
        return "will return a form view for creating a post w/button(s)<br><h1>Click Submit to create Post</h1><form action='/posts/create' method='POST'><button>submit</button></form>";
    }

    @PostMapping("/posts/create")
    public String createPostPost(){
        System.out.println("will run code to create a new post then send back a redirect to desired page. (index, posts, post/{id}");
        return "redirect:/posts";
    }

}
