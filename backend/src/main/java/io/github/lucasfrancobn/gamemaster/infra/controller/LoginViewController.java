package io.github.lucasfrancobn.gamemaster.infra.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginViewController {
    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
