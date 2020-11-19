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
public class Scene {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "scene_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "composer_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
    private User composer;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "score_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
    private Score score;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "voice_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
    private Voice voice;

    // @OneToMany(mappedBy = "scene") 
    // private List<Scene> scenes;

    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "scene_tag", joinColumns = @JoinColumn(name = "scene_id"), inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private List<Tag> tags;

    private String name;

    @CreationTimestamp
    private Date createdAt;

    public Scene() {}

    public Long getId() {
        return this.id;
    }

    public User getComposer() {
        return composer;
    }

    public void setComposer(User composer) {
        this.composer = composer;
    }

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }

    public Voice getVoice() {
        return voice;
    }

    public void setVoice(Voice voice) {
        this.voice = voice;
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

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "Scene [composer=" + composer + ", createdAt=" + createdAt + ", name=" + name + ", score=" + score
                + ", tags=" + tags + ", voice=" + voice + "]";
    }
}
