package com.interstakt.interstaktweb.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "tag_id")
    private Long id;

    private String phrase;

    @ManyToMany(mappedBy = "tags")
    private List<Score> tagScores;

    @ManyToMany(mappedBy = "tags")
    private List<Voice> tagVoices;

    @ManyToMany(mappedBy = "tags")
    private List<Scene> tagScenes;


    public Tag() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhrase() {
        return this.phrase;
    }

    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }


    public List<Score> getTagScores() {
        return this.tagScores;
    }

    public void setTagScores(List<Score> tagScores) {
        this.tagScores = tagScores;
    }

    public List<Voice> getTagVoices() {
        return this.tagVoices;
    }

    public void setTagVoices(List<Voice> tagVoices) {
        this.tagVoices = tagVoices;
    }

    public List<Scene> getTagScenes() {
        return this.tagScenes;
    }

    public void setTagScenes(List<Scene> tagScenes) {
        this.tagScenes = tagScenes;
    }
    
}
