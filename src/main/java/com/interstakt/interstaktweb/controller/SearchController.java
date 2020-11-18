package com.interstakt.interstaktweb.controller;

import java.util.ArrayList;
import java.util.List;

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
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SearchController {
    @Autowired
    private UserService userService;
	
    @Autowired
    private ScoreService scoreService;

    @Autowired
    private VoiceService voiceService;

    @Autowired
    private SceneService sceneService;

    @GetMapping(value="/search")
    public String search(Model model){
        User user = userService.getLoggedInUser();
        List<Score> scores = scoreService.findAllByUser(user);
        List<Voice> voices = voiceService.findAllByUser(user);
        List<Scene> scenes = sceneService.findAllByUser(user);

        List<String> scoreList = new ArrayList<String>();
        List<List<String>> voiceList = new ArrayList<List<String>>();
        List<List<String>> sceneList = new ArrayList<List<String>>();

        for (int i=0; i < scores.size(); i++) {
            scoreList.add(i, scores.get(i).getTitle());
        }

        for (int i=0; i < voices.size(); i++) {
            List<String> voiceParams = new ArrayList<String>();

            voiceParams.add(voices.get(i).getName());
            voiceParams.add(voices.get(i).getScore().getTitle());

            voiceList.add(i, voiceParams);
        }

        for (int i=0; i < scenes.size(); i++) {
            List<String> sceneParams = new ArrayList<String>();

            sceneParams.add(scenes.get(i).getName());
            sceneParams.add(scenes.get(i).getScore().getTitle());
            sceneParams.add(scenes.get(i).getVoice().getName());

            sceneList.add(i, sceneParams);
        }

        model.addAttribute("user", user);
        model.addAttribute("scoreList", scoreList);
        model.addAttribute("voiceList", voiceList);
        model.addAttribute("sceneList", sceneList);
        

        return "search";
    }
}
