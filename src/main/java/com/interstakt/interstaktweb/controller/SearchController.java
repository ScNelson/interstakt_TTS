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
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/{username}")
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
        List<Score> scores = scoreService.findAllByUserOrderTitle(user);
        List<Voice> voices = voiceService.findAllByUserOrderName(user);
        List<Scene> scenes = sceneService.findAllByUserOrderName(user);

        List<List<String>> scoreList = new ArrayList<List<String>>();
        List<List<String>> voiceList = new ArrayList<List<String>>();
        List<List<String>> sceneList = new ArrayList<List<String>>();

        for (int i=0; i < scores.size(); i++) {
            List<String> scoreParams = new ArrayList<String>();

            scoreParams.add(scores.get(i).getId().toString());
            scoreParams.add(scores.get(i).getTitle());

            scoreList.add(i, scoreParams);
        }

        for (int i=0; i < voices.size(); i++) {
            List<String> voiceParams = new ArrayList<String>();

            voiceParams.add(voices.get(i).getScore().getId().toString());
            voiceParams.add(voices.get(i).getId().toString());
            voiceParams.add(voices.get(i).getName());
            voiceParams.add(voices.get(i).getScore().getTitle());

            voiceList.add(i, voiceParams);
        }

        for (int i=0; i < scenes.size(); i++) {
            List<String> sceneParams = new ArrayList<String>();

            sceneParams.add(scenes.get(i).getScore().getId().toString());
            sceneParams.add(scenes.get(i).getVoice().getId().toString());
            sceneParams.add(scenes.get(i).getId().toString());
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
