package com.interstakt.interstaktweb.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class Voice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "voice_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "composer_id")
    private User composer;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "score_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
    private Score score;

    @OneToMany(mappedBy = "voice") 
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Scene> scenes;

    private String name;

    @CreationTimestamp
    private Date createdAt;

    public Voice() {}

    public Long getId() {
        return id;
    }

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }

    public User getUser() {
        return composer;
    }

    public void setUser(User user) {
        this.composer = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
    
}
