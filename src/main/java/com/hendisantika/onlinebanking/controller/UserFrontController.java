package com.hendisantika.onlinebanking.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.stream.Collectors;

@Controller
public class UserFrontController {

    @GetMapping("/userFront")
    public String userFront(Authentication authentication, Model model) {
        String roles = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .filter(authority -> authority.startsWith("ROLE_"))
                .collect(Collectors.joining(", "));
        model.addAttribute("username", authentication.getName());
        model.addAttribute("authorities", roles);
        return "userFront";
    }
}
