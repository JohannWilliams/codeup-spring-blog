package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.models.Post;
import com.codeup.codeupspringblog.models.User;
import com.codeup.codeupspringblog.repositories.PostRepository;
import com.codeup.codeupspringblog.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {

    private final PostRepository postDao;

    private final UserRepository userDao;

    public PostController(PostRepository postDao, UserRepository userDao) {
        this.postDao = postDao;
        this.userDao = userDao;
    }

    @GetMapping({"/posts", "/posts/"})
    public String getPosts(Model model){
        model.addAttribute("posts", postDao.findAll());
        return "posts/index";
    }

    @GetMapping({"/posts/{id}", "/posts/{id}/"})
    public String getPosts(@PathVariable String id, Model model){
        model.addAttribute("post", postDao.findPostById(Long.parseLong(id)));
        return "posts/show";
    }

    @GetMapping({"/posts/create", "/posts/create/"})
    public String createPostView(Model model){
        model.addAttribute("post", new Post());
        return "posts/create";
    }

    @PostMapping({"/posts/create", "/posts/create/"})
    public String createPostPost(@ModelAttribute Post post){
        User user = userDao.findUserById(1L);
        post.setUser(user);
        postDao.save(post);
        return "redirect:/posts";
    }

    @GetMapping({"/posts/{id}/edit", "/posts/{id}/edit/"})
    public String editPostView(Model model, @PathVariable String id){
        Post post = postDao.findPostById(Long.parseLong(id));
        model.addAttribute("post", post);
        return "posts/edit";
    }

    @PostMapping({"/posts/{id}/edit", "/posts/{id}/edit/"})
    public String editPost(@ModelAttribute Post post){
        Post postToUpdate = postDao.findPostById(post.getId());
        postToUpdate.setTitle(post.getTitle());
        postToUpdate.setBody(post.getBody());
        postDao.save(postToUpdate);
        String redirectString = "redirect:/posts/" + post.getId();
        return redirectString;
    }

}
