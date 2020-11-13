package com.interstakt.interstaktweb.repository;

import java.util.List;

import com.interstakt.interstaktweb.model.Score;
import com.interstakt.interstaktweb.model.User;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScoreRepository extends CrudRepository<Score, Long> {
    List<Score> findAllByOrderByCreatedAtDesc();

    List<Score> findAllByUserOrderByCreatedAtDesc(User user);
}
