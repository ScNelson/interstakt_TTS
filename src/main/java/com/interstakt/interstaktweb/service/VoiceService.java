package com.interstakt.interstaktweb.service;

import java.util.List;
import java.util.Optional;

import com.interstakt.interstaktweb.model.Score;
import com.interstakt.interstaktweb.model.User;
import com.interstakt.interstaktweb.model.Voice;
import com.interstakt.interstaktweb.repository.VoiceRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoiceService {
    @Autowired
    private VoiceRepository voiceRepository;

    public Voice find(Long id) {
        Optional<Voice> voice = voiceRepository.findById(id);
        Voice result = voice.get();
        return result;
    }

    public List<Voice> findAll() {
        List<Voice> voices = voiceRepository.findAllByOrderByCreatedAtDesc();
        return voices;
    }

    public List<Voice> findAllByUser(User user) {
        List<Voice> voices = voiceRepository.findAllByComposerOrderByCreatedAtDesc(user);
        return voices;
    }

    public List<Voice> findAllByUserOrderName(User user) {
        List<Voice> voices = voiceRepository.findAllByComposerOrderByName(user);
        return voices;
    }

    public List<Voice> findAllByScore(Score score) {
        List<Voice> voices = voiceRepository.findAllByScoreOrderByCreatedAtDesc(score);
        return voices;
    }

    public void save(Voice voice) {
        voiceRepository.save(voice);
    }

    public void delete(long id) {
        voiceRepository.deleteById(id);
    }
}
