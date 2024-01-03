package ru.mirea.musicsite.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.mirea.musicsite.entities.Song;
import ru.mirea.musicsite.services.BrowseService;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("")
public class PlayerController {

    private final BrowseService browseService;

    public PlayerController(BrowseService browseService) {
        this.browseService = browseService;
    }


    @PostMapping("/playSong")
    public String playSong(HttpServletRequest request,
                           @RequestParam int song_id,
                           RedirectAttributes redirectAttributes) {

        Song song = browseService.showSong(song_id);

        String playing_song_src = "/static/mp3/" + song.getSong_file();
        String playing_song_author = browseService.showArtist(song.getMain_artist_id()).getNickname();
        String playing_song_name = song.getName();

        redirectAttributes.addFlashAttribute("playing_song_src", playing_song_src);
        redirectAttributes.addFlashAttribute("playing_song_author", playing_song_author);
        redirectAttributes.addFlashAttribute("playing_song_name", playing_song_name);

        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }
}