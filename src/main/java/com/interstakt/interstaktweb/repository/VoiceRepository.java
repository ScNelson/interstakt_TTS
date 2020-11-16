package com.interstakt.interstaktweb.repository;

import java.util.List;

import com.interstakt.interstaktweb.model.Score;
import com.interstakt.interstaktweb.model.User;
import com.interstakt.interstaktweb.model.Voice;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface VoiceRepository extends CrudRepository<Voice, Long>{
    Voice findByName(String name);

    List<Voice> findByScoreId(Long id);

    List<Voice> findAllByOrderByCreatedAtDesc();

    List<Voice> findAllByComposerOrderByCreatedAtDesc(User user);

    List<Voice> findAllByScoreOrderByCreatedAtDesc(Score score);
}
