package ru.mirea.musicsite.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.mirea.musicsite.entities.SongInLibrary;
import ru.mirea.musicsite.security.entities.User;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("")
public class ProjectController {

    @GetMapping("/project_DB")
    public String project() {
        return "project_DB";
    }

    @GetMapping("")
    public String redirectProject() {
        return "redirect:/project_DB";
    }
}