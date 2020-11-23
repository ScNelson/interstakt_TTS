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
@RequestMapping("/{userName}/score/{title}")
public class ScoreController {

    @Autowired
    private UserService userService;

    @Autowired
    private ScoreService scoreService;

    @Autowired
    private VoiceService voiceService;

    private User loggedInUser;

    private Score currScore;

    public void addScoreAttributes(Long id, Model model) {
        loggedInUser = userService.getLoggedInUser();
        currScore = scoreService.find(id);
        List<Voice> voices = voiceService.findAllByScore(currScore);
        Voice voice = new Voice();
        model.addAttribute("user", loggedInUser);
        model.addAttribute("score", currScore);
        model.addAttribute("voice", voice);
        model.addAttribute("voiceList", voices);
        model.addAttribute("voiceCount", voices.size());
        model.addAttribute("sceneCount", scoreService.sceneCount(currScore.getId()));
        model.addAttribute("level", "score");
    }

    @GetMapping(value = { "/{id}" })
    public String getScoreWithID(@PathVariable Long id, Model model) {
        addScoreAttributes(id, model);
        return "score";
    }

    @RequestMapping(value = "/edit/{id}")
    public String editScoreWithId(@PathVariable Long id, Model model) {

        addScoreAttributes(id, model);

        return "editScore";
    }

    @RequestMapping(value = "/update/{id}")
    public String updateExistingScore(@PathVariable Long id, Score score, Model model) {
        Score updatedScore = scoreService.find(id);
        updatedScore.setTitle(score.getTitle());
        if (score.getImgURL() != "") {
            updatedScore.setImgURL(score.getImgURL());
        } else {
            updatedScore.setImgURL(null);
        }
        scoreService.save(updatedScore);
        model.addAttribute("score", updatedScore);

        addScoreAttributes(id, model);

        return "score";
    }

    @PostMapping(value = "/{id}")
    public String submitVoiceForm(@Valid Voice voice, @PathVariable Long id, BindingResult bindingResult, Model model) {
        currScore = scoreService.find(id);
        voice.setScore(currScore);
        voice.setComposer(currScore.getComposer());
        voiceService.save(voice);
        addScoreAttributes(id, model);
        return "redirect:/" + loggedInUser.getUsername() + "/score/" + currScore.getTitle() + "/" + id;
    }

    @RequestMapping(value = "/delete/{id}-{voiceId}")
    public String deleteVoiceWithID(@PathVariable Long id, @PathVariable Long voiceId, Model model) {
        voiceService.delete(voiceId);

        addScoreAttributes(id, model);

        return "redirect:/" + loggedInUser.getUsername() + "/score/" + currScore.getTitle() + "/" + id;
    }
}
