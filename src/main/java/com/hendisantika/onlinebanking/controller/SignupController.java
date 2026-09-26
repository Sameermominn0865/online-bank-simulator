package com.hendisantika.onlinebanking.controller;

import com.hendisantika.onlinebanking.entity.User;
import com.hendisantika.onlinebanking.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SignupController {

    private final UserService userService;

    public SignupController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/signup")
    public String signupForm(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute("user") User user, Model model) {
        try {
            User saved = userService.registerUser(user);
            model.addAttribute("user", saved);
            return "signupSuccess";
        } catch (IllegalArgumentException ex) {
            user.setPassword("");
            model.addAttribute("user", user);
            model.addAttribute("error", ex.getMessage());
            return "signup";
        }
    }
}
