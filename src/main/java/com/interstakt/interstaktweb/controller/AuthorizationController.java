package com.interstakt.interstaktweb.controller;

import javax.validation.Valid;

import com.interstakt.interstaktweb.model.User;
import com.interstakt.interstaktweb.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthorizationController {
	
	@Autowired
    private UserService userService;

    @GetMapping(value="/login")
    public String login(Model model){
        User user = userService.getLoggedInUser();
        if (userService.getLoggedInUser() != null) {
            model.addAttribute("user", user);
        }
        return "login";
    }

    @GetMapping(value = "/")
    public String index(Model model) {
        User user = userService.getLoggedInUser();
        if (userService.getLoggedInUser() != null) {
            model.addAttribute("user", user);
        }
        return "main";
    }

    @GetMapping(value = "/about")
    public String about(Model model) {
        User user = userService.getLoggedInUser();
        if (userService.getLoggedInUser() != null) {
            model.addAttribute("user", user);
        }
        return "about";
    }
    
    @GetMapping(value="/signup")
    public String registration(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "registration";
    }

    @PostMapping(value = "/signup")
    public String createNewUser(@Valid User user, BindingResult bindingResult, Model model) {
        User userExists = userService.findByUsername(user.getUsername());
        if (userExists != null) {
            bindingResult.rejectValue("username", "error.user", "Username is already taken");
        }
        if (!bindingResult.hasErrors()) {
            userService.saveNewUser(user);
            model.addAttribute("success", "Sign up successful!");
            model.addAttribute("user", new User());
        }
        return "registration";
    }
}
