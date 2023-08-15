package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.models.Post;
import com.codeup.codeupspringblog.models.User;
import com.codeup.codeupspringblog.models.UserWithRoles;
import com.codeup.codeupspringblog.repositories.PostRepository;
import com.codeup.codeupspringblog.repositories.UserRepository;
import com.codeup.codeupspringblog.services.EmailService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {

    private final EmailService emailService;

    private final PostRepository postDao;

    private final UserRepository userDao;

    public PostController(PostRepository postDao, UserRepository userDao, EmailService emailService) {
        this.postDao = postDao;
        this.userDao = userDao;
        this.emailService = emailService;
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
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        post.setUser(user);
        postDao.save(post);

        emailService.sendPostEmail(post, "Your new post!", "Ok your post is up");
        return "redirect:/posts";
    }

    @GetMapping({"/posts/{id}/edit", "/posts/{id}/edit/"})
    public String editPostView(Model model, @PathVariable String id){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Post post = postDao.findPostById(Long.parseLong(id));
        if(user.getId() == post.getUser().getId()){
            model.addAttribute("post", post);
            model.addAttribute("edit", true);
            return "posts/edit";
        }else {
            return "redirect:/posts";
        }
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

    @GetMapping({"/posts/{id}/delete", "/posts/{id}/delete/"})
    public String deletePostView(Model model, @PathVariable String id){
        System.out.println("id = " + id + " in delete get mapping");
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println("user = " + user + " in delete get mapping");

        Post post = postDao.findPostById(Long.parseLong(id));
        System.out.println("post = " + post + " in delete get mapping");

        if(user.getId() == post.getUser().getId()){
            System.out.println("User == posts user");
            model.addAttribute("post", post);
            return "posts/delete";
        }else {
            System.out.println("User does not == posts user");
            return "redirect:/posts";
        }
    }

    @PostMapping({"/posts/{id}/delete","/posts/{id}/delete/"})
    public String deletePost(@PathVariable String id){
        Post postToDelete = postDao.findPostById(Long.parseLong(id));
        postDao.delete(postToDelete);
        return "redirect:/posts";
    }
}
