package ru.mirea.musicsite.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.mirea.musicsite.entities.AlbumInLibrary;
import ru.mirea.musicsite.entities.Song;
import ru.mirea.musicsite.entities.SongInLibrary;
import ru.mirea.musicsite.repositories.SongRepository;
import ru.mirea.musicsite.security.entities.User;
import ru.mirea.musicsite.services.BrowseService;

import javax.servlet.http.HttpServletRequest;


@Controller
@ControllerAdvice
@RequestMapping("")
public class ButtonsController {

    @Autowired
    private BrowseService browseService;

    @Autowired
    private SongRepository songRepository;

    private String playing_song_src;
    private String playing_song_author;
    private String playing_song_name;

    private User currentUser;


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

    @PostMapping("/addSong")
    public String addSong(HttpServletRequest request,
                          Authentication auth,
                          @RequestParam int song_id) {

        if (auth != null)
            currentUser = (User)auth.getPrincipal();
        else
            currentUser = null;


        SongInLibrary songInLibrary;
        if (currentUser != null)
            songInLibrary = new SongInLibrary(currentUser.getUser_id(), song_id);
        else
            songInLibrary = new SongInLibrary(0, song_id);

        browseService.addSongInLibrary(songInLibrary);

        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }

    @PostMapping("/checkoutSong")
    public String checkoutSong(HttpServletRequest request,
                               Authentication auth,
                               @RequestParam int song_id) {

        if (auth != null)
            currentUser = (User)auth.getPrincipal();
        else
            currentUser = null;


        SongInLibrary songInLibrary;
        if (currentUser != null)
            songInLibrary = new SongInLibrary(currentUser.getUser_id(), song_id);
        else
            songInLibrary = new SongInLibrary(0, song_id);

        browseService.deleteSongInLibrary(songInLibrary);

        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }

    @PostMapping("/addAlbum")
    public String addAlbum(HttpServletRequest request,
                           Authentication auth,
                           @RequestParam int album_id) {

        if (auth != null)
            currentUser = (User)auth.getPrincipal();
        else
            currentUser = null;


        AlbumInLibrary albumInLibrary;
        if (currentUser != null)
            albumInLibrary = new AlbumInLibrary(currentUser.getUser_id(), album_id);
        else
            albumInLibrary = new AlbumInLibrary(0, album_id);

        browseService.addAlbumInLibrary(albumInLibrary);

        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }

    @PostMapping("/checkoutAlbum")
    public String checkoutAlbum(HttpServletRequest request,
                                Authentication auth,
                                @RequestParam int album_id) {

        if (auth != null)
            currentUser = (User)auth.getPrincipal();
        else
            currentUser = null;


        AlbumInLibrary albumInLibrary;
        if (currentUser != null)
            albumInLibrary = new AlbumInLibrary(currentUser.getUser_id(), album_id);
        else
            albumInLibrary = new AlbumInLibrary(0, album_id);

        browseService.deleteAlbumInLibrary(albumInLibrary);

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