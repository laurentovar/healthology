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


    @GetMapping("admin_profile")
    public String getAdminProfile(Model model){

        //Get all the admin userIDs;
        List<Admin> adminList = adminDao.findAll();
        //List<Long> adminUserIds = adminDao.GetAllAdminUserIDs();

        ArrayList<User> x = new ArrayList<>();
        x.add(adminList.get(0).getUser_id());
        x.add(adminList.get(1).getUser_id());
        //try find all in admins (.findall)

        //Get all the users that do not have these adminIDS
        List<User> allUsersList = userDao.getNonAdminUsers(x);

        model.addAttribute("users", allUsersList);



        return "admin/admin_profile";
    }

}
