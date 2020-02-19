package com.example.healthology.controllers;

import com.example.healthology.models.Admin;
import com.example.healthology.models.User;
import com.example.healthology.repositories.AdminRepository;
import com.example.healthology.repositories.ClientRepository;
import com.example.healthology.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ChatroomController {


    private final AdminRepository adminDao;
    private final UsersRepository userDao;
    private final ClientRepository clientDao;

    @Value("${talkjs.api.key}")
    private String tjapi;



    public ChatroomController(AdminRepository adminDao, UsersRepository userDao, ClientRepository clientDao) {
        this.adminDao = adminDao;
        this.userDao = userDao;
        this.clientDao = clientDao;
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
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() != "anonymousUser") {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
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
                model.addAttribute(user.getUsername(), user);
            for (int i = 0; i < allUsersList.size()-1; i++) {
                User other = allUsersList.get(i);
                model.addAttribute(other.getUsername(), other);
            }

            User user3 = userDao.getOne(3L);
            model.addAttribute("user3", user3);
            User user4 = userDao.getOne(4L);
            model.addAttribute("user4", user4);
            model.addAttribute("tjapi", tjapi);


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

            return "Depression";
        } else {
            return "users/login";
        }
    }
}
