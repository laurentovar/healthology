package com.example.healthology.controllers;

import com.example.healthology.models.Admin;
import com.example.healthology.models.Client;
import com.example.healthology.models.Client_history;
import com.example.healthology.models.User;
import com.example.healthology.repositories.AdminRepository;
import com.example.healthology.repositories.ClientRepository;
import com.example.healthology.repositories.UsersRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminController {

    private final AdminRepository adminDao;
    private final UsersRepository userDao;
    private final ClientRepository clientDao;


    public AdminController(AdminRepository adminDao, UsersRepository userDao, ClientRepository clientDao) {
        this.adminDao = adminDao;
        this.userDao = userDao;
        this.clientDao = clientDao;
    }


    @GetMapping("/profileAdmin/{id}")
    public String otherProfile(@PathVariable long id, Model model){
        model.addAttribute("user", userDao.getOne(id));
        return "admin/adminOtherProfile";

    }


    @GetMapping("admin_profile")
    public String getAdminProfile(Model model){

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


        return "admin/admin_profile";
    }

}
