package com.example.healthology.controllers;

import com.example.healthology.models.Client;
import com.example.healthology.models.Journal;
import com.example.healthology.models.User;
import com.example.healthology.repositories.ClientRepository;
import com.example.healthology.repositories.JournalRepository;
import com.example.healthology.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProfileController {


    private JournalRepository journalDao;
    private UsersRepository userDao;
    private ClientRepository clientDao;


    @Value("${filestack.api.key}")
    private String fsapi;

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
        model.addAttribute("fsapi", fsapi);

        if (user.getUsername().equalsIgnoreCase("admin")){
            return "redirect:/admin_profile";
            //return "admin/admin_profile";
        }
        else {
            return "users/profile";
        }
    }

    @PostMapping("/users/{id}/edit")
    public String editProfile(@PathVariable long id, @ModelAttribute User user){
        User updatedUser = userDao.getOne(id);
        updatedUser.setAbout_me(user.getAbout_me());
        userDao.save(updatedUser);
        return "redirect:/profile";
    }

    @PostMapping("/users/{id}/photo")
    public String editPhoto(@PathVariable long id,
                            @RequestParam(name = "profile_img") String profile_img){
        User updatedUser = userDao.getOne(id);
        updatedUser.setProfile_img(profile_img);
        userDao.save(updatedUser);
        return "redirect:/profile";
    }

    @PostMapping("/users/{id}/delete")
    public String deleteProfile(@PathVariable long id, @ModelAttribute User user){
//        clientDao.deleteById(user.getClient().getId());
        userDao.deleteById(id);
        return "redirect:/";
    }

//    @PostMapping("/users/{id}/photo")
//    public String editPhoto(@PathVariable long id, @ModelAttribute User user){
//        User updatedUser = userDao.getOne(id);
//        updatedUser.setProfile_img(user.getProfile_img());
//        userDao.save(updatedUser);
//        return "redirect:/profile";
//    }


    @PostMapping("/journal/create")
    public String submitPost(@ModelAttribute Journal journal){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Client client = clientDao.findClientByUser_id(user);
        journal.setClient(client);
        journalDao.saveAndFlush(journal);
        return "redirect:/profile";
    }



}
