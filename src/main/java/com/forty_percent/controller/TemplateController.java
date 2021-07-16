package com.forty_percent.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class TemplateController {

    @GetMapping("register")
    public String getRegisterView() {
        return "register";
    }

    @GetMapping("login")
    public String getLoginView() {
        return "login";
    }

    @GetMapping("profile")
    public String getProfileView() {
        return "profile";
    }
}
