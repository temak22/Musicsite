package ru.mirea.musicsite.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.mirea.musicsite.DtoConverter;
import ru.mirea.musicsite.entities.Album;
import ru.mirea.musicsite.entities.Artist;
import ru.mirea.musicsite.entities.Song;
import ru.mirea.musicsite.security.entities.User;
import ru.mirea.musicsite.services.SearchService;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private SearchService searchService;

    @Autowired
    private DtoConverter converter;


    private User currentUser;


    @GetMapping("")
    public String searchForm(Authentication auth,
                             @RequestParam(required = false, defaultValue = "") String filter,
                             Model model) {

        if (auth != null)
            currentUser = (User)auth.getPrincipal();
        else
            currentUser = null;


        List<Artist> artists = new ArrayList<>();
        List<Album> albums = new ArrayList<>();
        List<Song> songs = new ArrayList<>();
        if (filter != null && !filter.isEmpty()) {
            artists = searchService.showArtistsByPartName(filter);
            albums = searchService.showAlbumsByPartName(filter);
            songs = searchService.showSongsByPartName(filter);
            model.addAttribute("filter", filter);
        }
        else
            model.addAttribute("filter", null);


        model.addAttribute("artists", artists);
        model.addAttribute("albumsInBrowse", converter.convertAlbumsToAlbumDtoList(albums));
        model.addAttribute("songsInBrowse", converter.convertSongsToSongDtoList(songs, currentUser));

        return "main/search";
    }

    @GetMapping("/filter={filter}/albums")
    public String searchAlbums(@PathVariable String filter,
                               Model model) {

        List<Album> albums = new ArrayList<>();
        if (filter != null && !filter.isEmpty()) {
            albums = searchService.showAlbumsByPartName(filter);
            model.addAttribute("filter", filter);
        }
        else
            model.addAttribute("filter", null);

        model.addAttribute("albumsInBrowse", converter.convertAlbumsToAlbumDtoList(albums));

        return "main/browseAlbums";
    }

    @GetMapping("/filter={filter}/artists")
    public String searchArtists(@PathVariable String filter,
                                Model model) {

        List<Artist> artists = new ArrayList<>();
        if (filter != null && !filter.isEmpty()) {
            artists = searchService.showArtistsByPartName(filter);
            model.addAttribute("filter", filter);
        }
        else
            model.addAttribute("filter", null);

        model.addAttribute("artists", artists);

        return "main/browseArtists";
    }

    @GetMapping("/filter={filter}/songs")
    public String searchSongs(Authentication auth,
                              @PathVariable String filter,
                              Model model) {

        if (auth != null)
            currentUser = (User)auth.getPrincipal();
        else
            currentUser = null;


        List<Song> songs = new ArrayList<>();
        if (filter != null && !filter.isEmpty()) {
            songs = searchService.showSongsByPartName(filter);
            model.addAttribute("filter", filter);
        }
        else
            model.addAttribute("filter", null);

        model.addAttribute("songsInBrowse", converter.convertSongsToSongDtoList(songs, currentUser));

        return "main/browseSongs";
    }
}