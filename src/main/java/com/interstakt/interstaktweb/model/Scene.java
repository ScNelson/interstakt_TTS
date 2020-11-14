package com.interstakt.interstaktweb.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class Scene {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "scene_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "user_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "score_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
    private Score score;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "voice_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
    private Voice voice;
}
