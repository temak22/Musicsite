package ru.mirea.musicsite.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.mirea.musicsite.DtoConverter;
import ru.mirea.musicsite.entities.AlbumInLibrary;
import ru.mirea.musicsite.entities.SongInLibrary;
import ru.mirea.musicsite.security.entities.User;
import ru.mirea.musicsite.services.LibraryService;

import java.util.List;


@Controller
@RequestMapping("/library")
public class LibraryController {

    @Autowired
    private LibraryService libraryService;

    @Autowired
    private DtoConverter converter;


    @GetMapping("")
    public String libraryHome() {
        return "library/library";
    }

    @GetMapping("/songs")
    public String librarySongs(Authentication auth,
                               Model model) {

        User realUser = (User)auth.getPrincipal();

        List<SongInLibrary> songsInLibrary = libraryService.showSongsInLibraryByUserId(realUser.getUser_id());
        model.addAttribute("songsInLibrary", converter.convertSongsInLibraryToSongDtoList(songsInLibrary));

        return "library/librarySongs";
    }

    @GetMapping("/albums")
    public String libraryAlbums(Authentication auth,
                                Model model) {

        User realUser = (User)auth.getPrincipal();

        List<AlbumInLibrary> albumsInLibrary = libraryService.showAlbumsInLibraryByUserId(realUser.getUser_id());
        model.addAttribute("albumsInLibrary", converter.convertAlbumsInLibraryToAlbumDtoList(albumsInLibrary));

        return "library/libraryAlbums";
    }
}