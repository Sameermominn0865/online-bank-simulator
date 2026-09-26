package com.hendisantika.onlinebanking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(@RequestParam(value = "error", required = false) String error,
                       @RequestParam(value = "logout", required = false) String logout,
                       Model model) {
        model.addAttribute("message", "Online Bank Simulator is running.");
        if (error != null) {
            model.addAttribute("loginError", "Invalid username or password.");
        }
        if (logout != null) {
            model.addAttribute("logoutMessage", "You are logged out.");
        }
        return "index";
    }
}
