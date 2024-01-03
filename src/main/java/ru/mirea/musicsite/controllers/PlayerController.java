package ru.mirea.musicsite.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.mirea.musicsite.entities.Song;
import ru.mirea.musicsite.services.BrowseService;

import javax.servlet.http.HttpServletRequest;


@Controller
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
}