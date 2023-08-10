package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.models.Ad;
import com.codeup.codeupspringblog.models.User;
import com.codeup.codeupspringblog.repositories.AdRepository;
import com.codeup.codeupspringblog.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdController {
    private final AdRepository adDao;
    private final UserRepository userDao;

    public AdController(AdRepository adDao, UserRepository userDao) {
        this.adDao = adDao;
        this.userDao = userDao;
    }

    @GetMapping({"/ads", "/ads/"})
    public String adsIndex(Model model){
        model.addAttribute("ads", adDao.findAll());
        return "ads/index";
    }

    @GetMapping({"/ads/{id}", "/ads/{id}/"})
    public String showAd(@PathVariable String id, Model model){
        model.addAttribute("ad", adDao.findAdById(Long.parseLong(id)));
        return "ads/show";
    }

    @GetMapping({"/ads/create", "/ads/create/"})
    public String adsCreateIndex(Model model){
        model.addAttribute("ad", new Ad());
        return "ads/create";
    }

    @PostMapping({"/ads/create", "/ads/create/"})
    public String adsPostIndex(@ModelAttribute Ad ad){
        User user = userDao.findUserById(1L);
        ad.setUser(user);
        adDao.save(ad);
        return "redirect:/ads";
    }
}
