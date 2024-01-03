package ru.mirea.musicsite.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.mirea.musicsite.entities.Album;
import ru.mirea.musicsite.entities.Artist;
import ru.mirea.musicsite.entities.Song;
import ru.mirea.musicsite.entities.SongInLibrary;
import ru.mirea.musicsite.security.entities.User;
import ru.mirea.musicsite.services.BrowseService;
import ru.mirea.musicsite.services.LibraryService;
import ru.mirea.musicsite.dtos.SongDto;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/library")
public class LibraryController {

    private final LibraryService libraryService;
    private final BrowseService browseService;

    private String playing_song_src;
    private String playing_song_author;
    private String playing_song_name;

    public LibraryController(LibraryService libraryService, BrowseService browseService) {
        this.libraryService = libraryService;
        this.browseService = browseService;
    }


    @GetMapping("")
    public String libraryHome() {
        return "library/library";
    }

    @GetMapping("/songs")
    public String librarySongs(Authentication auth,
                               Model model) {

        User realUser = (User)auth.getPrincipal();

        List<SongInLibrary> songsInLibrary = libraryService.showSongsInLibraryByUserId(realUser.getUser_id());
        model.addAttribute("songsInBrowse", convertToSongDtoList(songsInLibrary));

        model.addAttribute("playing_song_src", playing_song_src);
        model.addAttribute("playing_song_author", playing_song_author);
        model.addAttribute("playing_song_name", playing_song_name);

        return "library/librarySongs";
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


    private ArrayList<SongDto> convertToSongDtoList(List<SongInLibrary> songsInLibrary) {
        ArrayList<SongDto> songList = new ArrayList<>();

        for (SongInLibrary songInLibrary : songsInLibrary) {
            int song_id = songInLibrary.getSong_id();

            Song song = libraryService.showSong(song_id);
            Artist artist = libraryService.showArtist(song.getMain_artist_id());
            Album album = libraryService.showAlbumBySongId(song_id);

            songList.add(new SongDto(song.getSong_id(), song.getName(), artist, album, 1));
        }

        return songList;
    }
}