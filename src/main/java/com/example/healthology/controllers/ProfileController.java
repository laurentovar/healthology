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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        model.addAttribute("user", user);
        model.addAttribute("journals", journalDao.findAll());
        model.addAttribute("journal", new Journal());

        if (user.getUsername().equalsIgnoreCase("admin")){
            return "redirect:/admin_profile";
            //return "admin/admin_profile";
        }
        else {
            return "users/profile";

        }
    }


    @PostMapping("/edit")
    public String editProfile(@RequestParam(name = "aboutMe") String aboutMe, @ModelAttribute User user){

        User updatedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        User newUser = userDao.findByUsername(newUser.getUsername());
        updatedUser.setAbout_me(aboutMe);
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
