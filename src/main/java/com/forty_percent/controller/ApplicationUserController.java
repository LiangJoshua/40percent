package com.forty_percent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

import com.forty_percent.service.ApplicationUserService;

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
	public RedirectView register(
			@RequestParam ("username") String username,
			@RequestParam ("password") String password) {
		applicationUserService.register(username, password);
		return new RedirectView("login");
	}
}