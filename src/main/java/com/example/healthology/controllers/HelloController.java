package com.example.healthology.controllers;

import com.example.healthology.models.User;
import com.example.healthology.repositories.UsersRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {



	@GetMapping("/hello")
	public String hello(Model model){
			return "HelloWorld";
	}

}
