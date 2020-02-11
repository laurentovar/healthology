package com.example.healthology.controllers;

import com.example.healthology.models.Client;
import com.example.healthology.models.Journal;
import com.example.healthology.models.User;
import com.example.healthology.repositories.ClientRepository;
import com.example.healthology.repositories.JournalRepository;
import com.example.healthology.repositories.UsersRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProfileController {


    private JournalRepository journalDao;
    private UsersRepository userDao;
    private ClientRepository clientDao;


    public ProfileController(JournalRepository journalDao, UsersRepository userDao, ClientRepository clientDao) {
        this.journalDao = journalDao;
        this.userDao = userDao;
        this.clientDao = clientDao;
    }

    @GetMapping("/profile")
    public String postsIndex(Model model){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", userDao.getOne(user.getId()));
        model.addAttribute("journals", journalDao.findAll());
        model.addAttribute("journal", new Journal());
        return "users/profile";
    }

    @PostMapping("/users/{id}/edit")
    public String editProfile(@PathVariable long id, @ModelAttribute User user){
        User updatedUser = userDao.getOne(id);
//        User updatedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        User newUser = userDao.findByUsername(newUser.getUsername());
        updatedUser.setAbout_me(user.getAbout_me());
        userDao.save(updatedUser);
        return "redirect:/profile";
    }


    @PostMapping("/journal/create")
    public String submitPost(@ModelAttribute Journal journal){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Client client = clientDao.findClientByUser_id(user);
        journal.setClient(client);
        journalDao.saveAndFlush(journal);
        return "redirect:/profile";
    }



}
