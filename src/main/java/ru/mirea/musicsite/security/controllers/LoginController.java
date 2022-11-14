package ru.mirea.musicsite.security.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.mirea.musicsite.security.entities.Role;
import ru.mirea.musicsite.security.entities.User;
import ru.mirea.musicsite.security.repositories.UserRepo;

import java.util.Collections;
import java.util.Map;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(User user, Map<String, Object> model) {
        return "redirect:browse";
    }
}
