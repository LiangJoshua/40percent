package com.forty_percent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.forty_percent.dto.UserDto;
import com.forty_percent.model.User;
import com.forty_percent.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController{

	@Autowired
	private UserService userService;

	@PostMapping (value = "/register")
	@ResponseBody
	public String register(@RequestBody UserDto userDto) {
		userService.registerUser(userDto.getUsername(), userDto.getEmail(), userDto.getPassword());
		return "Registered";
	}

	@PostMapping(value = "/login")
	@ResponseBody
	public String login(@RequestBody UserDto userDto) {
		User user = userService.loadUserByUsername(userDto.getUsername());
		if (user == null) {
			return "User doesn't exist";
		}
		if (!user.getPassword().equals(userDto.getPassword())) {
			return "Password incorrect";
		}

		return "Logged in!";
	}
}
