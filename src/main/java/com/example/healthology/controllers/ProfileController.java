package com.example.healthology.controllers;

import com.example.healthology.repositories.JournalRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileController {


    private JournalRepository journalDao;

    public ProfileController(JournalRepository journalDao) {
        this.journalDao = journalDao;
    }

    @GetMapping("/profile")
    public String postsIndex(Model model){
        model.addAttribute("journals", journalDao.findAll());
        return "users/profile";
    }

}
