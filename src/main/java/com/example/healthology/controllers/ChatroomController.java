package com.example.healthology.controllers;

import com.example.healthology.models.*;
import com.example.healthology.repositories.AdminRepository;
import com.example.healthology.repositories.ClientRepository;
import com.example.healthology.repositories.GroupRepository;
import com.example.healthology.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ChatroomController {


    private final AdminRepository adminDao;
    private final UsersRepository userDao;
    private final ClientRepository clientDao;
    private GroupRepository groupDao;

    @Value("${talkjs.api.key}")
    private String tjapi;


    public ChatroomController(AdminRepository adminDao, UsersRepository userDao, ClientRepository clientDao, GroupRepository groupDao) {
        this.adminDao = adminDao;
        this.userDao = userDao;
        this.clientDao = clientDao;
        this.groupDao = groupDao;
    }

    @GetMapping("/chatroom")
    public String chatroom(Model model){
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() != "anonymousUser") {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            model.addAttribute("user", user);
            User user2 = userDao.getOne(2L);
            model.addAttribute("user2", user2);
            User user3 = userDao.getOne(3L);
            model.addAttribute("user3", user3);
            User user4 = userDao.getOne(4L);
            model.addAttribute("user4", user4);
            model.addAttribute("tjapi", tjapi);

            //Get all the admin userIDs;
            List<Admin> adminList = adminDao.findAll();
            //List<Long> adminUserIds = adminDao.GetAllAdminUserIDs();

            ArrayList<User> x = new ArrayList<>();
            x.add(adminList.get(0).getUser_id());

            //try find all in admins (.findall)

            //Get all the users that do not have these adminIDS
            List<User> allUsersList = userDao.getNonAdminUsers(x);

            //Empty List for users with clients
            ArrayList<User> usersWithClient = new ArrayList<>();

            for (int i =0; i <= allUsersList.size()-1; i++){
                //Check if they have client, client history and client contact. If they exist add to list
                if (allUsersList.get(i).getClient() != null
                        && allUsersList.get(i).getClient().getClient_history() != null
                        && allUsersList.get(i).getClient().getClient_contact() != null
                        && allUsersList.get(i).getClient().getGroups() != null
                ){
                    usersWithClient.add(allUsersList.get(i));
                }
            }

            model.addAttribute("users", usersWithClient);

            return "chatroom";
        } else {
            return "users/login";
        }
    }

    @GetMapping("/Depression")
    public String depressionChatroom(Model model){
        model.addAttribute("group", groupDao.getOne(1L));

        List<Client> allClientsWithDepression = clientDao.getAllClientsByGroups(groupDao.getOne(1L));

        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() != "anonymousUser") {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            model.addAttribute("user", user);

            model.addAttribute("tjapi", tjapi);

            model.addAttribute("members", allClientsWithDepression );

            return "groups/Depression";
        } else {
            return "users/login";
        }
    }

    @GetMapping("/PTSD")
    public String ptsdChatroom(Model model){

        Group PTSD = groupDao.getOne(2L);

        List<Client> allClientsWithPTSD = clientDao.getAllClientsByGroups(PTSD);

        model.addAttribute("group", PTSD);

        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() != "anonymousUser") {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            model.addAttribute("user", user);

            model.addAttribute("tjapi", tjapi);

            model.addAttribute("members", allClientsWithPTSD );

            return "groups/PTSD";
        } else {
            return "users/login";
        }
    }

    @GetMapping("/Anxiety")
    public String anxietyChatroom(Model model){

        Group Anxiety = groupDao.getOne(3L);

        List<Client> allClientsWithAnxiety = clientDao.getAllClientsByGroups(Anxiety);

        model.addAttribute("group", Anxiety);

        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() != "anonymousUser") {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            model.addAttribute("user", user);

            model.addAttribute("tjapi", tjapi);

            model.addAttribute("members", allClientsWithAnxiety );

            return "groups/Anxiety";
        } else {
            return "users/login";
        }
    }

    @GetMapping("/OCD")
    public String ocdChatroom(Model model){

        Group OCD = groupDao.getOne(4L);

        List<Client> allClientsWithOCD = clientDao.getAllClientsByGroups(OCD);

        model.addAttribute("group", OCD);

        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() != "anonymousUser") {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            model.addAttribute("user", user);

            model.addAttribute("tjapi", tjapi);

            model.addAttribute("members", allClientsWithOCD );

            return "groups/OCD";
        } else {
            return "users/login";
        }
    }

    @GetMapping("/Eating-Disorders")
    public String eatingChatroom(Model model){

        Group eating = groupDao.getOne(5L);

        List<Client> allClientsWithED = clientDao.getAllClientsByGroups(eating);

        model.addAttribute("group", eating);

        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() != "anonymousUser") {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            model.addAttribute("user", user);

            model.addAttribute("tjapi", tjapi);

            model.addAttribute("members", allClientsWithED );

            return "groups/Eating-Disorders";
        } else {
            return "users/login";
        }
    }

    @GetMapping("/Insomnia")
    public String insomniaChatroom(Model model){

        Group insomnia = groupDao.getOne(6L);

        List<Client> allClientsWithInsomnia = clientDao.getAllClientsByGroups(insomnia);

        model.addAttribute("group", insomnia);

        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() != "anonymousUser") {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            model.addAttribute("user", user);

            model.addAttribute("tjapi", tjapi);

            model.addAttribute("members", allClientsWithInsomnia );

            return "groups/Insomnia";
        } else {
            return "users/login";
        }
    }

    @GetMapping("/Postpartum")
    public String postpartumChatroom(Model model){

        Group postpartum = groupDao.getOne(7L);

        List<Client> allClientsWithPostpartum = clientDao.getAllClientsByGroups(postpartum);

        model.addAttribute("group", postpartum);

        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() != "anonymousUser") {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            model.addAttribute("user", user);

            model.addAttribute("tjapi", tjapi);

            model.addAttribute("members", allClientsWithPostpartum );

            return "groups/Postpartum";
        } else {
            return "users/login";
        }
    }

    @GetMapping("/users.json/1")
    @ResponseBody
    public List<User> depressionObjects() {
        List<Client> allClientsWithDepression = clientDao.getAllClientsByGroups(groupDao.getOne(1L));
        List<User> users = new ArrayList<>();
        for (int i = 0; i < allClientsWithDepression.size(); i++) {
            users.add(allClientsWithDepression.get(i).getUser());
        }
        return users;
    }

    @GetMapping("/users.json/2")
    @ResponseBody
    public List<User> PTSDObjects() {
        List<Client> allClientsWithPTSD = clientDao.getAllClientsByGroups(groupDao.getOne(2L));
        List<User> users = new ArrayList<>();
        for (int i = 0; i < allClientsWithPTSD.size(); i++) {
            users.add(allClientsWithPTSD.get(i).getUser());
        }
        return users;
    }

    @GetMapping("/users.json/3")
    @ResponseBody
    public List<User> AnxietyObjects() {
        List<Client> allClientsWithAnxiety = clientDao.getAllClientsByGroups(groupDao.getOne(3L));
        List<User> users = new ArrayList<>();
        for (int i = 0; i < allClientsWithAnxiety.size(); i++) {
            users.add(allClientsWithAnxiety.get(i).getUser());
        }
        return users;
    }

    @GetMapping("/users.json/4")
    @ResponseBody
    public List<User> OCDObjects() {
        List<Client> allClientsWithOCD = clientDao.getAllClientsByGroups(groupDao.getOne(4L));
        List<User> users = new ArrayList<>();
        for (int i = 0; i < allClientsWithOCD.size(); i++) {
            users.add(allClientsWithOCD.get(i).getUser());
        }
        return users;
    }

    @GetMapping("/users.json/5")
    @ResponseBody
    public List<User> EatingObjects() {
        List<Client> allClientsWithED = clientDao.getAllClientsByGroups(groupDao.getOne(5L));
        List<User> users = new ArrayList<>();
        for (int i = 0; i < allClientsWithED.size(); i++) {
            users.add(allClientsWithED.get(i).getUser());
        }
        return users;
    }

    @GetMapping("/users.json/6")
    @ResponseBody
    public List<User> InsomniaObjects() {
        List<Client> allClientsWithInsomnia = clientDao.getAllClientsByGroups(groupDao.getOne(6L));
        List<User> users = new ArrayList<>();
        for (int i = 0; i < allClientsWithInsomnia.size(); i++) {
            users.add(allClientsWithInsomnia.get(i).getUser());
        }
        return users;
    }

    @GetMapping("/users.json/7")
    @ResponseBody
    public List<User> PostpartumObjects() {
        List<Client> allClientsWithPostpartum = clientDao.getAllClientsByGroups(groupDao.getOne(7L));
        List<User> users = new ArrayList<>();
        for (int i = 0; i < allClientsWithPostpartum.size(); i++) {
            users.add(allClientsWithPostpartum.get(i).getUser());
        }
        return users;
    }

}