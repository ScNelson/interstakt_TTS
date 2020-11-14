package com.interstakt.interstaktweb.repository;

import java.util.List;

import com.interstakt.interstaktweb.model.Scene;
import com.interstakt.interstaktweb.model.Score;
import com.interstakt.interstaktweb.model.User;
import com.interstakt.interstaktweb.model.Voice;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SceneRepository extends CrudRepository<Scene, Long> {
    Scene findByName(String name);

    List<Scene> findAllByOrderByCreatedAtDesc();

    List<Scene> findAllByUserOrderByCreatedAtDesc(User user);

    List<Scene> findAllByScoreOrderByCreatedAtDesc(Score score);

    List<Scene> findAllByVoiceOrderByCreatedAtDesc(Voice voice);
}
