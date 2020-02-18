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
    private ClientHistoryRepository clientHistoryDao;
    private ClientContactRepository clientContactDao;


    @Value("${filestack.api.key}")
    private String fsapi;

    public ProfileController(JournalRepository journalDao, UsersRepository userDao, ClientRepository clientDao, GroupRepository groupDao, ClientHistoryRepository clientHistoryDao, ClientContactRepository clientContactDao) {
        this.journalDao = journalDao;
        this.userDao = userDao;
        this.clientDao = clientDao;
        this.groupDao = groupDao;
        this.clientHistoryDao = clientHistoryDao;
        this.clientContactDao = clientContactDao;
    }

    @GetMapping("/profile")
    public String postsIndex(Model model){


        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();


        if (user.getUsername().equalsIgnoreCase("admin")){
            return "redirect:/admin_profile";
        }
        else {
        Client client = clientDao.findClientByUser_id(user);


        List<Journal> journals = journalDao.getAllJournalsByClientId(client.getId());
        List<Group> groups = groupDao.getAllGroupsByClients(client);

        Client_history client_history = client.getClient_history();
        Client_contact client_contact = client.getClient_contact();

        model.addAttribute("user", userDao.getOne(user.getId()));
        model.addAttribute("journals", journals);
        model.addAttribute("groups", groups);
        model.addAttribute("client_history", client_history);
        model.addAttribute("client_contact", client_contact);
        model.addAttribute("client_id", client.getId());
        model.addAttribute("journal", new Journal());
        model.addAttribute("fsapi", fsapi);


            for (int i = 0; i <= journals.size() - 1; i++) {
                System.out.println(journals.get(i).getRating());
//                System.out.println(journals.get(i).getDate());
        }


            return "users/profile";
        }


    }

    @GetMapping("/profile/{id}")
    public String otherProfile(@PathVariable long id, Model model){
        model.addAttribute("user", userDao.getOne(id));
            return "users/otherProfile";

    }


    @PostMapping("/users/{id}/edit")
    public String editProfile(@PathVariable long id, @ModelAttribute User user,
                              @ModelAttribute Client_history client_history, @ModelAttribute Client_contact client_contact){
        User updatedUser = userDao.getOne(id);
        updatedUser.setAbout_me(user.getAbout_me());

        Client updatedClient = clientDao.findClientByUser_id(updatedUser);
        Client_history updatedHistory = clientHistoryDao.getOne(updatedClient.getId());
        Client_contact updatedContact = clientContactDao.getOne(updatedClient.getId());

        updatedHistory.setDescription(client_history.getDescription());
        updatedHistory.setOtherInformation(client_history.getOtherInformation());
        updatedHistory.setPreviousCounseling(client_history.getPreviousCounseling());

        updatedContact.setEmergency_contact_name(client_contact.getEmergency_contact_name());
        updatedContact.setEmergency_contact_number(client_contact.getEmergency_contact_number());
        updatedContact.setProvider_name(client_contact.getProvider_name());
        updatedContact.setProvider_contact_number(client_contact.getProvider_contact_number());


        clientHistoryDao.save(updatedHistory);
        clientContactDao.save(updatedContact);
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


//    @PostMapping("/users/{id}/delete")
//    public String deleteProfile(@PathVariable long id, @ModelAttribute User user, HttpSession session){
//        userDao.delete(user);
//        session.invalidate();
//        return "redirect:/";
//    }




    @PostMapping("/journal/create")
    public String submitPost(@ModelAttribute Journal journal){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Client client = clientDao.findClientByUser_id(user);
        journal.setClient(client);
        journalDao.saveAndFlush(journal);
        return "redirect:/profile";
    }

    @GetMapping("/journals.json/{id}")
    @ResponseBody
    public List<Journal> journalObjects (@PathVariable Long id) {
        return journalDao.getAllJournalsByClientId(id);
    }



}
