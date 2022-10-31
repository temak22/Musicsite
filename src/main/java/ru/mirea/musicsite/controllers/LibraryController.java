package ru.mirea.musicsite.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.mirea.musicsite.entities.Album;
import ru.mirea.musicsite.entities.Artist;
import ru.mirea.musicsite.entities.Song;
import ru.mirea.musicsite.entities.SongInLibrary;
import ru.mirea.musicsite.security.entities.User;
import ru.mirea.musicsite.services.LibraryService;
import ru.mirea.musicsite.services.SearchService;
import ru.mirea.musicsite.viewEntity.AlbumInBrowse;
import ru.mirea.musicsite.viewEntity.SongInBrowse;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/library")
public class LibraryController {

    @Autowired
    private LibraryService libraryService;

    @GetMapping("")
    public String libraryHome(Model model) {
        return "library/library";
    }

    @GetMapping("/songs")
    public String librarySongs(Authentication auth,
                              Model model) {

        User realUser = (User)auth.getPrincipal();
        List<SongInLibrary> songsInLibrary = libraryService.showSongsInLibraryByUserId(realUser.getUser_id());
        ArrayList<SongInBrowse> songsInBrowse = new ArrayList<>();

        for (SongInLibrary songInLibrary : songsInLibrary) {
            int song_id = songInLibrary.getSong_id();
            Song song = libraryService.showSong(song_id);
            int artist_id = song.getMain_artist_id();
            Artist artist = libraryService.showArtist(artist_id);
            Album album = libraryService.showAlbumBySongId(song_id);
            songsInBrowse.add(new SongInBrowse(song.getSong_id(), song.getName(), artist, album, 1));
        }

        model.addAttribute("songsInBrowse", songsInBrowse);
        return "library/librarySongs";
    }
}