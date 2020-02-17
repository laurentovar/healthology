package com.example.healthology.controllers;

import com.example.healthology.models.*;
import com.example.healthology.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ProfileController {


    private JournalRepository journalDao;
    private UsersRepository userDao;
    private ClientRepository clientDao;
    private GroupRepository groupDao;


    @Value("${filestack.api.key}")
    private String fsapi;

    public ProfileController(JournalRepository journalDao, UsersRepository userDao, ClientRepository clientDao,
                             GroupRepository groupDao) {
        this.journalDao = journalDao;
        this.userDao = userDao;
        this.clientDao = clientDao;
        this.groupDao = groupDao;
    }

    @GetMapping("/profile")
    public String postsIndex(Model model){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Client client = clientDao.findClientByUser_id(user);

//        System.out.println(client.getId());
        List<Journal> journals = journalDao.getAllJournalsByClientId(client.getId());
        List<Group> groups = groupDao.getAllGroupsByClients(client);


        model.addAttribute("user", userDao.getOne(user.getId()));
        model.addAttribute("journals", journals);
        model.addAttribute("groups", groups);
        model.addAttribute("client", client);
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

    @GetMapping("/profile/{id}")
    public String otherProfile(@PathVariable long id, Model model){
        model.addAttribute("user", userDao.getOne(id));
            return "users/otherProfile";

    }

//    @GetMapping("/test/j")
//    public String testJournal(){
//        List<Journal> journals = journalDao.getAllJournalsByClientId(1L);
//        for (int i = 0; i < journals.size(); i++){
//            System.out.println(journals.get(i).getId());
//        }
//        return "/aboutMe";
//    }

    @PostMapping("/users/{id}/edit")
    public String editProfile(@PathVariable long id, @ModelAttribute User user, @ModelAttribute Client client){
        User updatedUser = userDao.getOne(id);
        updatedUser.setAbout_me(user.getAbout_me());

        Client updatedClient = clientDao.getOne(updatedUser.getClient().getId());

        updatedClient.setClient_contact(client.getClient_contact());
        updatedClient.setClient_history(client.getClient_history());
        user.setClient(updatedClient);
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
    public String deleteProfile(@PathVariable long id, @ModelAttribute User user, HttpSession session){
        userDao.delete(user);
        session.invalidate();
        return "redirect:/";
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
