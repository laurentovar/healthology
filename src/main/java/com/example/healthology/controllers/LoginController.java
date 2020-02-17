package com.example.healthology.controllers;

import com.example.healthology.models.Client;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        Authentication token = SecurityContextHolder.getContext().getAuthentication();
        if (token instanceof AnonymousAuthenticationToken) return "users/login";

        // Redirect to the configured home page
        return String.format("redirect:%s", "/profile");
    }


    @PostMapping("/login")
    public String loggedIn (@RequestParam String username,
                            @RequestParam String password,
                            Model model) {
        model.addAttribute("username", username);
        model.addAttribute("password", password);


            return "users/profile";

        }
}
