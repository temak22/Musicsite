package ru.mirea.musicsite.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.mirea.musicsite.entities.Song;
import ru.mirea.musicsite.services.BrowseService;

import javax.servlet.http.HttpServletRequest;


@Controller
@ControllerAdvice
@RequestMapping("")
public class PlayerController {

    private final BrowseService browseService;

    private String playing_song_src;
    private String playing_song_author;
    private String playing_song_name;


    public PlayerController(BrowseService browseService) {
        this.browseService = browseService;
    }


    @PostMapping("/playSong")
    public String playSong(HttpServletRequest request,
                           @RequestParam int song_id) {

        Song song = browseService.showSong(song_id);

        playing_song_src = "/static/mp3/" + song.getSong_file();
        playing_song_author = browseService.showArtist(song.getMain_artist_id()).getNickname();
        playing_song_name = song.getName();

        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }


    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("playing_song_src", playing_song_src);
        model.addAttribute("playing_song_author", playing_song_author);
        model.addAttribute("playing_song_name", playing_song_name);
    }
}