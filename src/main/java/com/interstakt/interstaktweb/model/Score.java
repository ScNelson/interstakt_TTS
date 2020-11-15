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
public class Score {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "score_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "composer_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
    private User composer;

    @OneToMany(mappedBy = "score") 
    private List<Voice> voices;

    private String title;
    
    @CreationTimestamp
    private Date createdAt;

    public Score() {}

    public Score(User user, List<Voice> voices, String title, Date createdAt) {
        this.composer = user;
        this.voices = voices;
        this.title = title;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return composer;
    }

    public void setUser(User user) {
        this.composer = user;
    }

    public List<Voice> getVoices() {
        return voices;
    }

    public void setVoices(List<Voice> voices) {
        this.voices = voices;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Score [createdAt=" + createdAt + ", title=" + title + ", user=" + composer + ", voices=" + voices + "]";
    }
}