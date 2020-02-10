package com.example.healthology.controllers;

import com.example.healthology.models.User;
import com.example.healthology.repositories.JournalRepository;
import com.example.healthology.repositories.UsersRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileController {


    private JournalRepository journalDao;
    private UsersRepository userDao;

    public ProfileController(JournalRepository journalDao, UsersRepository userDao) {
        this.journalDao = journalDao;
        this.userDao = userDao;
    }

    @GetMapping("/profile")
    public String postsIndex(Model model){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", user);
        model.addAttribute("journals", journalDao.findAll());
        return "users/profile";
    }

}
