package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.models.Post;
import com.codeup.codeupspringblog.repositories.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {

    private final PostRepository postDao;

    public PostController(PostRepository postDao) {
        this.postDao = postDao;
    }

    @GetMapping("/posts")
    public String getPosts(Model model){
        model.addAttribute("posts", postDao.findAll());
        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String getPosts(@PathVariable String id, Model model){
        model.addAttribute("post", postDao.getById(Long.parseLong(id)));
        return "posts/show";
    }

    @GetMapping("/posts/create")
    public String createPostView(){
        return "posts/create";
    }

    @PostMapping("/posts/create")
    public String createPostPost(@RequestParam(name = "title") String title, @RequestParam(name = "body") String body){
        postDao.save(new Post(title, body));
        return "redirect:/posts";
    }

}
