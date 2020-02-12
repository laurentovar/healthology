package com.example.healthology.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ResourcesController {

    @GetMapping("/resources")
    public String resources(){

        return "resources";

    }
}
