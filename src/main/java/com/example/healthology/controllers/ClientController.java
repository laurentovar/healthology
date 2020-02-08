package com.example.healthology.controllers;

import com.example.healthology.repositories.ClientRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ClientController {

    private final ClientRepository clientDao;

    public ClientController(ClientRepository clientDao) {
        this.clientDao = clientDao;
    }

    @GetMapping("client/client_setup")
    public String showClientSetup(){
        return "client/client_setup";
    }

}
