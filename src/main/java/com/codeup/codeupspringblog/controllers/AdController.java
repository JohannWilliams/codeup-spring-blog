package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.models.Ad;
import com.codeup.codeupspringblog.repositories.AdRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdController {
    private final AdRepository adDao;

    public AdController(AdRepository adDao) {
        this.adDao = adDao;
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
    public String adsCreateIndex(){
        return "ads/create";
    }

    @PostMapping({"/ads/create", "/ads/create/"})
    public String adsPostIndex(@RequestParam(name="title") String title, @RequestParam(name="description") String description, Model model){
        adDao.save(new Ad(title, description));
        return "redirect:/ads";
    }
}
