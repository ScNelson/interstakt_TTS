package com.interstakt.interstaktweb.controller;

import javax.validation.Valid;

import com.interstakt.interstaktweb.model.User;
import com.interstakt.interstaktweb.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthorizationController {
	
	@Autowired
    private UserService userService;

    public void checkLoggedIn(Model model) {
        User user = userService.getLoggedInUser();
        if (userService.getLoggedInUser() != null) {
            model.addAttribute("user", user);
        } 
    }

    @GetMapping(value="/login")
    public String login(Model model){
        checkLoggedIn(model);
        return "login";
    }

    @GetMapping(value = "/")
    public String index(Model model) {
        checkLoggedIn(model);
        return "main";
    }

    @GetMapping(value = "/about")
    public String about(Model model) {
        model.addAttribute("section", "about");
        checkLoggedIn(model);
        return "about";
    }

    @GetMapping(value = "/about/{section}")
    public String overview(@PathVariable String section, Model model) {
        model.addAttribute("section", section);
        checkLoggedIn(model);
        return "about";
    }

    @GetMapping(value = "/about/{section}/{subsection}")
    public String overviewSection(@PathVariable String section, @PathVariable String subsection, Model model) {
        model.addAttribute("section", section);
        model.addAttribute("subsection", subsection);
        checkLoggedIn(model);
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
