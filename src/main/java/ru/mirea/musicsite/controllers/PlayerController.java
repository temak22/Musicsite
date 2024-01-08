package ru.mirea.musicsite.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.mirea.musicsite.entities.Song;
import ru.mirea.musicsite.repositories.SongRepository;
import ru.mirea.musicsite.services.BrowseService;

import javax.servlet.http.HttpServletRequest;


@Controller
@ControllerAdvice
@RequestMapping("")
public class PlayerController {

    @Autowired
    private BrowseService browseService;

    @Autowired
    private SongRepository songRepository;

    private String playing_song_src;
    private String playing_song_author;
    private String playing_song_name;


    @PostMapping("/playSong")
    public String playSong(HttpServletRequest request,
                           @RequestParam int song_id) {

        Song song = browseService.showSong(song_id);
        int album_id = browseService.showAlbumBySongId(song_id).getAlbum_id();

        playing_song_src = String.format("/mp3/%s/%s", album_id, song.getSong_file());
        playing_song_author = browseService.showArtist(song.getMain_artist_id()).getNickname();
        playing_song_name = song.getName();

        songRepository.updateListening(song_id);

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