package com.codeup.codeupspringblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    @ResponseBody
    public String getPosts(){
        return "Will return all listed posts!";
    }

    @GetMapping("/posts/{id}")
    @ResponseBody
    public String getPosts(@PathVariable long id){
        return "Will return the post with an id of " + id;
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
