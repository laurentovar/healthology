package com.example.healthology.controllers;

import com.example.healthology.models.Client;
import com.example.healthology.models.Journal;
import com.example.healthology.models.User;
import com.example.healthology.repositories.JournalRepository;
import com.example.healthology.repositories.UsersRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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

    @GetMapping("/journals/create")
    public String createPostForm(Model model){
        model.addAttribute("journal", new Journal());
        return "journals/create";
    }


    @PostMapping("/journal/create")
    public String submitPost(@ModelAttribute Journal journal){
        Client client = (Client) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        journal.setClient(client);
        journalDao.save(journal);
        return "redirect:/profile/";
    }

}
