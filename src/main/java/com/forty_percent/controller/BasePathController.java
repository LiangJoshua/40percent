package com.forty_percent.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BasePathController {

	@RequestMapping("/")
	public String index() {
		return "Hello from Forty Percent";
	}
}
