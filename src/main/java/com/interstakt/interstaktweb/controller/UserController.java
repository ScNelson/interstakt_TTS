package com.interstakt.interstaktweb.controller;

import java.util.List;

import com.interstakt.interstaktweb.model.Score;
import com.interstakt.interstaktweb.model.User;
import com.interstakt.interstaktweb.service.ScoreService;
import com.interstakt.interstaktweb.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/{username}")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private ScoreService scoreService;

    @GetMapping(value = "/profile")
    public String getUser(@PathVariable(value = "username") String username, Model model) {
        User user = userService.findByUsername(username);
        List<Score> scores = scoreService.findAllByUser(user);
        model.addAttribute("scoreCount", scores.size());
        model.addAttribute("voiceCount", userService.getVoiceCount(user));
        model.addAttribute("sceneCount", userService.getSceneCount(user));
        // model.addAttribute("scoreList", scores);
        model.addAttribute("user", user);
        return "profile";
    }

}
