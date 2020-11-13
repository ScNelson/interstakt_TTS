package com.interstakt.interstaktweb.controller;

import java.util.List;

import javax.validation.Valid;

import com.interstakt.interstaktweb.model.Score;
import com.interstakt.interstaktweb.model.User;
import com.interstakt.interstaktweb.service.ScoreService;
import com.interstakt.interstaktweb.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ScoreController {
    @Autowired
    private UserService userService;
	
    @Autowired
    private ScoreService scoreService;
    
    @GetMapping(value= {"/scores", "/"})
    public String getFeed(Model model){
        List<Score> scores = scoreService.findAll();
        model.addAttribute("scoreList", scores);
        return "scores";
    }
    
    @GetMapping(value = "/scores/new")
    public String getTweetForm(Model model) {
        model.addAttribute("score", new Score());
        return "newScore";
    }
    
    @PostMapping(value = "/scores")
    public String submitTweetForm(@Valid Score score, BindingResult bindingResult, Model model) {
        User user = userService.getLoggedInUser();
        if (!bindingResult.hasErrors()) {
            score.setUser(user);
            scoreService.save(score);
            model.addAttribute("successMessage", "Score successfully created!");
            model.addAttribute("score", new Score());
        }
        return "newScore";
    }
}