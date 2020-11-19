package com.interstakt.interstaktweb.service;

import java.util.List;
import java.util.Optional;

import com.interstakt.interstaktweb.model.Score;
import com.interstakt.interstaktweb.model.User;
import com.interstakt.interstaktweb.repository.SceneRepository;
import com.interstakt.interstaktweb.repository.ScoreRepository;
import com.interstakt.interstaktweb.repository.VoiceRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScoreService {
    @Autowired
    private ScoreRepository scoreRepository;

    @Autowired
    private VoiceRepository voiceRepository;

    @Autowired
    private SceneRepository sceneRepository;

    public Score find(Long id) {
        Optional<Score> score = scoreRepository.findById(id);
        Score result = score.get();
        return result;
    }

    public List<Score> findAll() {
        List<Score> scores = scoreRepository.findAllByOrderByCreatedAtDesc();
        return scores;
    }

    public List<Score> findAllByUser(User user) {
        List<Score> scores = scoreRepository.findAllByComposerOrderByCreatedAtDesc(user);
        return scores;
    }

    public int voiceCount(Long id) {
        int count = voiceRepository.findByScoreId(id).size();
        return count;
    }

    public int sceneCount(Long id) {
        int count = sceneRepository.findByScoreId(id).size();
        return count;
    }

    public void save(Score score) {
        scoreRepository.save(score);
    }

    public void delete(long id) {
        scoreRepository.deleteById(id);
    }
}
