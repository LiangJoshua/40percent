package com.forty_percent.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.forty_percent.service.ApplicationUserService;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class ApplicationUserController{

	private final ApplicationUserService applicationUserService;
}