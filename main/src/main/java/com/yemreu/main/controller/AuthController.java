package com.yemreu.main.controller;


import com.yemreu.main.dto.UserRegisterDto;
import com.yemreu.main.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/login")
    public String login() {
        return "auth/login";
    }

    @GetMapping("/register")
    public String displayRegistrationForm(WebRequest request, Model model) {
        model.addAttribute("user", new UserRegisterDto());
        return "auth/register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") @Valid UserRegisterDto userRegisterDto, final BindingResult result) {
        if(result.hasErrors()) {
            return "auth/register";
        }
        userService.save(userRegisterDto);
        return "redirect:/auth/login";
    }

}
