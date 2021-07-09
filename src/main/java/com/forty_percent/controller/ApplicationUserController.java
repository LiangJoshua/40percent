package com.forty_percent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import com.forty_percent.service.ApplicationUserService;

@CrossOrigin(origins = "http://localhost:3000/", maxAge = 3600)
@Controller
@RequestMapping("/users")
public class ApplicationUserController{

	private final ApplicationUserService applicationUserService;

	@Autowired
	public ApplicationUserController(ApplicationUserService applicationUserService){
		this.applicationUserService = applicationUserService;
	}

	@PostMapping (value = "/register")
	@ResponseBody
	public void register(
			@RequestParam ("username") String username,
			@RequestParam ("password") String password) {
		applicationUserService.register(username, password);
	}
}