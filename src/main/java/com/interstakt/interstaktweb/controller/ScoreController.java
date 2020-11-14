package com.interstakt.interstaktweb.controller;

import java.util.List;

import javax.validation.Valid;

import com.interstakt.interstaktweb.model.Score;
import com.interstakt.interstaktweb.model.User;
import com.interstakt.interstaktweb.model.Voice;
import com.interstakt.interstaktweb.service.ScoreService;
import com.interstakt.interstaktweb.service.UserService;
import com.interstakt.interstaktweb.service.VoiceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ScoreController {
   
    @Autowired
    private UserService userService;
	
    @Autowired
    private ScoreService scoreService;

    @Autowired
    private VoiceService voiceService;
   
    @GetMapping(value= {"/score/{title}"})
    public String getScoreWithID(@PathVariable String title, Model model){
        User user = userService.getLoggedInUser();
        Score score = scoreService.find(title);
        List<Voice> voices = voiceService.findAllByScore(score);
        Voice voice = new Voice();
        model.addAttribute("user", user);
        model.addAttribute("score", score);
        model.addAttribute("voice", voice);
        model.addAttribute("voiceList", voices);
        return "score";
    }

    @PostMapping(value = "/score/{title}")
    public String submitVoiceForm(@Valid Voice voice, @PathVariable String title, BindingResult bindingResult, Model model) {
        Score score = scoreService.find(title);
        voice.setScore(score);
        voice.setUser(score.getUser());
        voiceService.save(voice);
        Voice newVoice = new Voice();
        List<Voice> voices = voiceService.findAllByScore(score);
        model.addAttribute("user", score.getUser());
        model.addAttribute("score", score);
        model.addAttribute("voice", newVoice);
        model.addAttribute("voiceList", voices);
        return "score";
    }

    @RequestMapping(value = "/score/{title}/delete/{id}")
    public String deleteVoiceWithID(@PathVariable String title, @PathVariable Long id, Model model) {
        voiceService.delete(id);

        Score score = scoreService.find(title);
        Voice newVoice = new Voice();
        List<Voice> voices = voiceService.findAllByScore(score);
        model.addAttribute("user", score.getUser());
        model.addAttribute("score", score);
        model.addAttribute("voice", newVoice);
        model.addAttribute("voiceList", voices);
        return "score";
    }
}
