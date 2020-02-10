package com.example.healthology.controllers;
import com.example.healthology.models.Client;
import com.example.healthology.models.Client_contact;
import com.example.healthology.models.Client_history;
import com.example.healthology.models.Users;
import com.example.healthology.repositories.ClientContactRepository;
import com.example.healthology.repositories.ClientHistoryRepository;
import com.example.healthology.repositories.ClientRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class ClientController {

    private final ClientRepository clientDao;
    private final ClientHistoryRepository clientHistoryDao;
    private final ClientContactRepository clientContactDao;

    public ClientController(ClientRepository clientDao, ClientHistoryRepository clientHistoryDao, ClientContactRepository clientContactDao) {
        this.clientDao = clientDao;
        this.clientHistoryDao = clientHistoryDao;
        this.clientContactDao = clientContactDao;
    }


    //======Client agree to terms=====
    @GetMapping("/client_setup/")
    public String showClientSetup( Model model) {
        Client client = new Client();
        model.addAttribute("clients", client);

        return "client/client_setup";
    }

    @PostMapping("/client_setup/")
    public String termCheck(@ModelAttribute Client client){

        //Check if they agreed to terms, if they have do the rest
        if(!client.getAgreed_to_terms()){
            //temp
            return "redirect:/register";
        }
        // Get the current User
        Users currentUser = (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        //Update the client's user_id using the current user
        client.setUser(currentUser);

        //Save the client
        clientDao.save(client);

        //Redirect to the client contact history page
        return "redirect:/client_history";


    }


    //=======Client History======
    @GetMapping("/client_history")
    public String showClientHistory(Model model) {
        Client_history client_history = new Client_history();
        model.addAttribute("clients_history", client_history);

        return "client/client_history";
    }



    @PostMapping("/client_history")
    public String clientHistorycheck(@ModelAttribute Client_history client_history) {

        //Get the current user
        Users currentUser = (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        //Get the current users ID
        Client client = clientDao.findClientByUser_id(currentUser);

        //clientHistory.setClient(client);
        client_history.setClient(client);

        //clientHistoryDao.save(clientHistory)
        clientHistoryDao.save(client_history);

        return "redirect:/client_contact";


    }

    //=======client contact info======
    @GetMapping("/client_contact")
    public String showClientContactInfo(Model model) {
        Client_contact client_contact = new Client_contact();
        model.addAttribute("clients_contact", client_contact);

        return "client/client_contact";
    }

    @PostMapping("/client_contact")
    public String clientContactCheck(@ModelAttribute Client_contact client_contact) {

        //Get the current user
        Users currentUser = (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        //Get the current users ID
        Client client = clientDao.findClientByUser_id(currentUser);

        //clientHistory.setClient(client);
        client_contact.setClient_id(client);

        //clientHistoryDao.save(clientHistory)
        clientContactDao.save(client_contact);

        return "users/profile";


    }



}
