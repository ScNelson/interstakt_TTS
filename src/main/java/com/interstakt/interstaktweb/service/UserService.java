package com.interstakt.interstaktweb.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import com.interstakt.interstaktweb.model.Role;
import com.interstakt.interstaktweb.model.User;
import com.interstakt.interstaktweb.repository.RoleRepository;
import com.interstakt.interstaktweb.repository.SceneRepository;
import com.interstakt.interstaktweb.repository.ScoreRepository;
import com.interstakt.interstaktweb.repository.UserRepository;
import com.interstakt.interstaktweb.repository.VoiceRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private ScoreRepository scoreRepository;

    @Autowired
    private VoiceRepository voiceRepository;

    @Autowired
    private SceneRepository sceneRepository;

    @Autowired
    public UserService(UserRepository userRepository, 
                       RoleRepository roleRepository,
                       BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
        
    public List<User> findAll(){
        return (List<User>) userRepository.findAll();
    }
        
    public void save(User user) {
        userRepository.save(user);
    }

    public User saveNewUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
        Role userRole = roleRepository.findByRole("USER");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        return userRepository.save(user);
    }

    public User getLoggedInUser() {
        String loggedInUsername = SecurityContextHolder.
          getContext().getAuthentication().getName();
        
        return findByUsername(loggedInUsername);
    }

    public int getScoreCount(User user) {
        int count = scoreRepository.findAllByComposerOrderByCreatedAtDesc(user).size();
        return count;
    }

    public int getVoiceCount(User user) {
        int count = voiceRepository.findAllByComposerOrderByCreatedAtDesc(user).size();
        return count;
    }

    public int getSceneCount(User user) {
        int count = sceneRepository.findAllByComposerOrderByCreatedAtDesc(user).size();
        return count;
    }
}