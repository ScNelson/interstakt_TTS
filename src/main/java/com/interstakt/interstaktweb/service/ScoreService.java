package com.interstakt.interstaktweb.service;

import java.util.List;

import com.interstakt.interstaktweb.model.Score;
import com.interstakt.interstaktweb.model.User;
import com.interstakt.interstaktweb.repository.ScoreRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScoreService {
    @Autowired
    private ScoreRepository scoreRepository;

    public Score find(String title) {
        Score score = scoreRepository.findByTitle(title);
        return score;
    }

    public List<Score> findAll() {
        List<Score> scores = scoreRepository.findAllByOrderByCreatedAtDesc();
        return scores;
    }

    public List<Score> findAllByUser(User user) {
        List<Score> scores = scoreRepository.findAllByComposerOrderByCreatedAtDesc(user);
        return scores;
    }

    public void save(Score score) {
        scoreRepository.save(score);
    }

    public void delete(long id) {
        scoreRepository.deleteById(id);
    }
}
