package com.interstakt.interstaktweb.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Scene {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "scene_id")
    private Long id;
}
