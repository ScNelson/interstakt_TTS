package com.interstakt.interstaktweb.service;

import java.util.List;

import com.interstakt.interstaktweb.model.Scene;
import com.interstakt.interstaktweb.model.Score;
import com.interstakt.interstaktweb.model.User;
import com.interstakt.interstaktweb.model.Voice;
import com.interstakt.interstaktweb.repository.SceneRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SceneService {
    
    @Autowired
    private SceneRepository sceneRepository;


    public List<Scene> findAll() {
        List<Scene> scenes = sceneRepository.findAllByOrderByCreatedAtDesc();
        return scenes;
    }

    public List<Scene> findAllByUser(User user) {
        List<Scene> scenes = sceneRepository.findAllByUserOrderByCreatedAtDesc(user);
        return scenes;
    }

    public List<Scene> findAllByScore(Score score) {
        List<Scene> scenes = sceneRepository.findAllByScoreOrderByCreatedAtDesc(score);
        return scenes;
    }

    public List<Scene> findAllByVoice(Voice voice) {
        List<Scene> scenes = sceneRepository.findAllByVoiceOrderByCreatedAtDesc(voice);
        return scenes;
    }

    public void save(Scene scene) {
        sceneRepository.save(scene);
    }

    public void delete(long id) {
        sceneRepository.deleteById(id);
    }
}
