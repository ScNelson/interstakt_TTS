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
public class SceneController {
    @Autowired
    private UserService userService;
	
    @Autowired
    private ScoreService scoreService;

    @Autowired
    private VoiceService voiceService;

    @Autowired
    private SceneService sceneService;
   
    @GetMapping(value= {"/score/{title}/{name}/{sceneName}"})
    public String getVoiceWithID(@PathVariable String title, @PathVariable String name, @PathVariable String sceneName, Model model){
        User user = userService.getLoggedInUser();
        Score score = scoreService.find(title);
        Voice voice = voiceService.find(name);
        Scene scene = sceneService.find(sceneName);
        //Scene scene = new Scene();
        model.addAttribute("user", user);
        model.addAttribute("score", score);
        model.addAttribute("voice", voice);
        //model.addAttribute("voiceList", sceneList);
        model.addAttribute("scene", scene);
        model.addAttribute("level", "scene");
        return "scene";
    }

    /*
    @PostMapping(value = "/score/{title}/{name}/{sceneName}")
    public String submitSceneForm(@Valid Scene scene, @PathVariable String title, @PathVariable String name, @PathVariable String sceneName,BindingResult bindingResult, Model model) {
        Score score = scoreService.find(title);
        Voice voice = voiceService.find(name);
        scene.setScore(voice.getScore());
        scene.setUser(voice.getUser());
        scene.setVoice(voice);
        System.out.println(title);
        System.out.println(name);
        sceneService.save(scene);
        Scene newScene = new Scene();
        List<Scene> scenes = sceneService.findAllByVoice(voice);
        model.addAttribute("user", score.getUser());
        model.addAttribute("score", score);
        model.addAttribute("voice", voice);
        model.addAttribute("scene", newScene);
        model.addAttribute("sceneList", scenes);
        return "scene";
    }

    @RequestMapping(value = "/score/{title}/{name}/delete/{id}")
    public String deleteSceneWithID(@PathVariable String title, @PathVariable String name, @PathVariable Long id, Model model) {
        sceneService.delete(id);

        Score score = scoreService.find(title);
        Voice voice = voiceService.find(name);
        Scene newScene = new Scene();
        List<Scene> sceneList = sceneService.findAllByVoice(voice);
        model.addAttribute("user", score.getUser());
        model.addAttribute("score", score);
        model.addAttribute("voice", voice);
        model.addAttribute("scene", newScene);
        model.addAttribute("sceneList", sceneList);
        return "scene";
    }
    */
}