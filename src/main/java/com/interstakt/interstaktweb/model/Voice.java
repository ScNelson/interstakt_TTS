package com.interstakt.interstaktweb.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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

    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "voice_tag", joinColumns = @JoinColumn(name = "voice_id"), inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private List<Tag> tags;

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

    public User getComposer() {
        return composer;
    }

    public void setComposer(User user) {
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

    public List<Scene> getScenes() {
        return scenes;
    }

    public void setScenes(List<Scene> scenes) {
        this.scenes = scenes;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "Voice [composer=" + composer + ", createdAt=" + createdAt + ", name=" + name + ", scenes=" + scenes
                + ", score=" + score + ", tags=" + tags + "]";
    }
}
