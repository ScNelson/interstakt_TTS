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
@RequestMapping("/{userName}/score/{title}/{name}")
public class VoiceController {
    @Autowired
    private UserService userService;
	
    @Autowired
    private ScoreService scoreService;

    @Autowired
    private VoiceService voiceService;

    @Autowired
    private SceneService sceneService;

    private User loggedInUser;

    private Score currScore;

    private Voice currVoice;

    public void addVoiceAttributes(Long id, Long voiceId, Model model){
        loggedInUser = userService.getLoggedInUser();
        currScore = scoreService.find(id);
        currVoice = voiceService.find(voiceId);
        List<Scene> sceneList = sceneService.findAllByVoice(currVoice);
        
        Scene scene = new Scene();

        model.addAttribute("user", loggedInUser);
        model.addAttribute("score", currScore);
        model.addAttribute("voice", currVoice);
        model.addAttribute("voiceCount", scoreService.voiceCount(currScore.getId()));
        model.addAttribute("scene", scene);
        model.addAttribute("sceneCount", sceneList.size());
        model.addAttribute("sceneList", sceneList);
        model.addAttribute("level", "voice");
    }
   
    @GetMapping(value= {"/{scoreId}-{voiceId}"})
    public String getVoiceWithID(@PathVariable Long scoreId, @PathVariable Long voiceId, @PathVariable String name, Model model){
        addVoiceAttributes(scoreId, voiceId, model);

        return "voice";
    }

    @PostMapping(value = "/{scoreId}-{voiceId}")
    public String submitSceneForm(@Valid Scene scene, @PathVariable Long scoreId, @PathVariable Long voiceId, @PathVariable String name, BindingResult bindingResult, Model model) {
        Voice voice = voiceService.find(voiceId);
        scene.setScore(voice.getScore());
        scene.setComposer(voice.getComposer());
        scene.setVoice(voice);
        sceneService.save(scene);

        addVoiceAttributes(scoreId, voiceId, model);
        
        return "voice";
    }

    @RequestMapping(value = "/delete/{scoreId}-{voiceId}-{sceneId}")
    public String deleteSceneWithID(@PathVariable Long scoreId, @PathVariable Long voiceId, @PathVariable Long sceneId, Model model) {
        sceneService.delete(sceneId);

        addVoiceAttributes(scoreId, voiceId, model);
        return "redirect:/" + loggedInUser.getUsername() + "/score/" + currScore.getTitle() + "/" + currVoice.getName() + "/" + scoreId + "-" + voiceId;
    }
}
