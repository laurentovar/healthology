package com.example.healthology.controllers;

import com.example.healthology.models.User;
import com.example.healthology.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    // These two next steps are often called dependency injection, where we create a Repository instance and initialize it in the controller class constructor.
    private final UsersRepository usersDao;
    private PasswordEncoder passwordEncoder;


//    @Value("${filestack.api.key}")
//    private String fsAPI;
//
//    @Value("${talkjs.api.key}")
//    private String tjAPI;

    public UserController(UsersRepository usersDao, PasswordEncoder passwordEncoder) {
        this.usersDao = usersDao;
        this.passwordEncoder = passwordEncoder;
    }


    @GetMapping("/register")
    public String showRegisterForm(Model model){
        model.addAttribute("users", new User());
        return "users/register";
    }

    @PostMapping("/register")
    public String saveUser(@RequestParam(name = "client_token") String client_token, @ModelAttribute User user){

        //Check if token is correct, If it is do this
        if (client_token.equals("ABC")){
            user.setProfile_img("");
            String hash = passwordEncoder.encode(user.getPassword());
            user.setPassword(hash);
            usersDao.saveAndFlush(user);
            //Users createdUser = usersDao.findByUsername(user.getUsername());

            //System.out.println(createdUser.getId());
            return "redirect:/login/";// + createdUser.getId();
        }

        //Else
        return "/HelloWorld";

    }


}
