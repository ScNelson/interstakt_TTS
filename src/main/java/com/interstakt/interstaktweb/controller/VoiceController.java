package com.interstakt.interstaktweb.controller;

import java.util.List;

import javax.validation.Valid;

import com.interstakt.interstaktweb.model.Scene;
import com.interstakt.interstaktweb.model.Score;
import com.interstakt.interstaktweb.model.User;
import com.interstakt.interstaktweb.model.Voice;
import com.interstakt.interstaktweb.service.SceneService;
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
public class VoiceController {
    @Autowired
    private UserService userService;
	
    @Autowired
    private ScoreService scoreService;

    @Autowired
    private VoiceService voiceService;

    @Autowired
    private SceneService sceneService;

    public void addVoiceAttributes(String title, String name, Model model){
        User user = userService.getLoggedInUser();
        Score score = scoreService.find(title);
        Voice voice = voiceService.find(name);
        List<Scene> sceneList = sceneService.findAllByVoice(voice);
        
        Scene scene = new Scene();

        model.addAttribute("user", user);
        model.addAttribute("score", score);
        model.addAttribute("voice", voice);
        model.addAttribute("voiceCount", score.getVoiceCount());
        model.addAttribute("scene", scene);
        model.addAttribute("sceneCount", sceneList.size());
        model.addAttribute("sceneList", sceneList);
        model.addAttribute("level", "voice");
    }
   
    @GetMapping(value= {"/score/{title}/{name}"})
    public String getVoiceWithID(@PathVariable String title, @PathVariable String name, Model model){
        addVoiceAttributes(title, name, model);

        return "voice";
    }

    @PostMapping(value = "/score/{title}/{name}")
    public String submitSceneForm(@Valid Scene scene, @PathVariable String title, @PathVariable String name, BindingResult bindingResult, Model model) {
        Voice voice = voiceService.find(name);
        scene.setScore(voice.getScore());
        scene.setUser(voice.getUser());
        scene.setVoice(voice);
        sceneService.save(scene);

        addVoiceAttributes(title, name, model);
        
        return "voice";
    }

    @RequestMapping(value = "/score/{title}/{name}/delete/{id}")
    public String deleteSceneWithID(@PathVariable String title, @PathVariable String name, @PathVariable Long id, Model model) {
        sceneService.delete(id);

        addVoiceAttributes(title, name, model);
        return "redirect:/score/" + title + "/" + name;
    }
}
