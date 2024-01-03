package ru.mirea.musicsite.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


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