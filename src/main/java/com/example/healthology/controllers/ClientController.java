package com.example.healthology.controllers;
import com.example.healthology.models.*;
import com.example.healthology.repositories.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@Controller
public class ClientController {

    private final ClientRepository clientDao;
    private final ClientHistoryRepository clientHistoryDao;
    private final ClientContactRepository clientContactDao;
    private final GroupClientRepository groupClientDao;
    private final GroupRepository groupDao;
    private final UsersRepository userDao;

    public ClientController(ClientRepository clientDao, ClientHistoryRepository clientHistoryDao, ClientContactRepository clientContactDao, GroupClientRepository groupClientDao, GroupRepository groupDao, UsersRepository userDao) {
        this.clientDao = clientDao;
        this.clientHistoryDao = clientHistoryDao;
        this.clientContactDao = clientContactDao;
        this.groupClientDao = groupClientDao;
        this.groupDao = groupDao;
        this.userDao = userDao;
    }


    //======Client agree to terms=====
    @GetMapping("/client_setup/")
    public String showClientSetup( Model model ){    //, @PathVariable String id) {
//      User user1 = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //User user = new User((User) userDao.findTopByOrderByIdDesc());

//      User user1 = new User((User) userDao.findOne());

        //.getOne


        Client client = new Client();
        model.addAttribute("clients", client);

        //model.addAttribute("userinfo", userDao.findUserById(Long.parseLong(id)));

        return "client/client_setup";
    }

    @PostMapping("/client_setup/")
    public String termCheck(@ModelAttribute Client client ){

        //Check if they agreed to terms, if they have do the rest
        if(!client.getAgreed_to_terms()){
            //temp
            return "redirect:/client_setup/";
        }
        // Get the current User
         User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
         //User user = new User((User) userDao.findTopByOrderByIdDesc());

         //User user = userDao.findUserById(Long.parseLong(id));

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
        User user = new User((User) userDao.findTopByOrderByIdDesc());


        Client_history client_history = new Client_history();
        model.addAttribute("clients_history", client_history);

        return "client/client_history";
    }



    @PostMapping("/client_history")
    public String clientHistorycheck(@ModelAttribute Client_history client_history) {

        //Get the current user
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

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
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        //Get the current users ID
        Client client = clientDao.findClientByUser_id(currentUser);

        //clientHistory.setClient(client);
        client_contact.setClient_id(client);

        //clientHistoryDao.save(clientHistory)
        clientContactDao.save(client_contact);

        return "redirect:/client_groupSelection";


    }

    //========Client group selection======
    @GetMapping("/client_groupSelection")
    public String clientGroups(Model model) {
        Group_client group_client = new Group_client();
        model.addAttribute("clients_group", group_client);

        return "client/client_groupSelection";
    }

    @PostMapping("/client_groupSelection")
    public String clientGroupCheck(//@ModelAttribute Group_client group_client,
                                   @RequestParam(name = "Depression",required = false) String Depression,
                                   @RequestParam(name = "PTSD",required = false) String PTSD,
                                   @RequestParam(name = "Anxiety",required = false) String Anxiety,
                                   @RequestParam(name = "OCD",required = false) String OCD,
                                   @RequestParam(name = "Eating disorders",required = false) String Eatingdisorders,
                                   @RequestParam(name = "Insomnia",required = false) String Insomnia,
                                   @RequestParam(name = "Postpartum",required = false) String Postpartum)
    {

        //Get the current user
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        //Get the current users ID
        Client client = clientDao.findClientByUser_id(currentUser);

        //Set The client ID for groupClient
        //group_client.setClient_id(client);

        //Set the groupID group Client
        ArrayList<String> groupOptions = new ArrayList<String>();
        groupOptions.add(Depression);
        groupOptions.add(PTSD);
        groupOptions.add(Anxiety);
        groupOptions.add(OCD);
        groupOptions.add(Eatingdisorders);
        groupOptions.add(Insomnia);
        groupOptions.add(Postpartum);


        for (int i=0; i <= groupOptions.size() - 1; i++){
            if (groupOptions.get(i) != null){
                System.out.println(groupOptions.get(i));

                //Create Instance of Group_client
                Group_client group_client = new Group_client();

                //Set Group_client.clientId to the client found outside loop
                group_client.setClient_id(client);

                //Get the group by ID
                Group group = groupDao.getOne(Long.parseLong(groupOptions.get(i)));

                //Set Group_client.group_id to the group found above
                group_client.setGroup_id(group);

                //Save Group_Client
                groupClientDao.save(group_client);

            }

        }

        return "redirect:/profile";

    }
}
