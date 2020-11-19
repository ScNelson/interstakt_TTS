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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/{userName}")
public class ScoresController {
    @Autowired
    private UserService userService;
	
    @Autowired
    private ScoreService scoreService;

    public void addComposerAttributes(Model model){
        List<Score> scores = scoreService.findAll();
        Score score = new Score();
        User user = userService.getLoggedInUser();
        model.addAttribute("scoreList", scores);
        model.addAttribute("scoreCount", scores.size());
        model.addAttribute("voiceCount", userService.getVoiceCount(user));
        model.addAttribute("sceneCount", userService.getSceneCount(user));
        model.addAttribute("score", score);
        model.addAttribute("user", user);
    }
    
    @GetMapping(value= {"/scores"})
    public String getScores(Model model){
        addComposerAttributes(model);
        return "scores";
    }
    
    @GetMapping(value = "/scores/new")
    public String getScoreForm(Model model) {
        model.addAttribute("score", new Score());
        return "newScore";
    }
    
    @PostMapping(value = "/scores")
    public String submitScoreForm(@Valid Score score, BindingResult bindingResult, Model model) {
        User user = userService.getLoggedInUser();
        if (!bindingResult.hasErrors()) {
            score.setComposer(user);
            scoreService.save(score);
            model.addAttribute("successMessage", "Score successfully created!");
            addComposerAttributes(model);
        }
        return "scores";
    }

    @RequestMapping(value = "/scores/delete/{id}")
    public String deleteScoreWithID(@PathVariable Long id, Score score, Model model) {
        scoreService.delete(id);
        addComposerAttributes(model);
        User user = userService.getLoggedInUser();
        
        return "redirect:/" + user.getUsername() + "/scores";
    }
}