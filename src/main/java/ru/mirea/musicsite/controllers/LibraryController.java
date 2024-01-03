package ru.mirea.musicsite.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.mirea.musicsite.entities.Album;
import ru.mirea.musicsite.entities.Artist;
import ru.mirea.musicsite.entities.Song;
import ru.mirea.musicsite.entities.SongInLibrary;
import ru.mirea.musicsite.security.entities.User;
import ru.mirea.musicsite.services.BrowseService;
import ru.mirea.musicsite.services.LibraryService;
import ru.mirea.musicsite.dtos.SongDto;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/library")
public class LibraryController {

    private final LibraryService libraryService;
    private final BrowseService browseService;


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

        return "library/librarySongs";
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