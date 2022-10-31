package ru.mirea.musicsite.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.mirea.musicsite.entities.Album;
import ru.mirea.musicsite.entities.Artist;
import ru.mirea.musicsite.entities.Song;
import ru.mirea.musicsite.security.entities.User;
import ru.mirea.musicsite.services.SearchService;
import ru.mirea.musicsite.viewEntity.AlbumInBrowse;
import ru.mirea.musicsite.viewEntity.SongInBrowse;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private SearchService searchService;

    @GetMapping("")
    public String searchForm(
            Authentication auth,
            @RequestParam(required = false, defaultValue = "") String filter,
            Model model) {

        User realUser = (User)auth.getPrincipal();
        List<Artist> artists = new ArrayList<>();
        List<Album> albums = new ArrayList<>();
        ArrayList<AlbumInBrowse> albumsInBrowse = new ArrayList<>();
        List<Song> songs = new ArrayList<>();
        ArrayList<SongInBrowse> songsInBrowse = new ArrayList<>();


        if (filter != null && !filter.isEmpty()) {
            artists = searchService.showArtistsByPartName(filter);
            albums = searchService.showAlbumsByPartName(filter);
            songs = searchService.showSongsByPartName(filter);
            model.addAttribute("filter", filter);
        }
        else
            model.addAttribute("filter", null);

        for (Album album : albums) {
            int artist_id = album.getArtist_id();
            Artist artist = searchService.showArtist(artist_id);
            albumsInBrowse.add(new AlbumInBrowse(album, artist));
        }

        for (Song song : songs) {
            int artist_id = song.getMain_artist_id();
            Artist artist = searchService.showArtist(artist_id);
            int song_id = song.getSong_id();
            Album album = searchService.showAlbumBySongId(song_id);
            boolean isInLibrary = searchService.isInLibrary(realUser.getUser_id(), song_id);
            int is_in_library;
            if (isInLibrary)
                is_in_library = 1;
            else
                is_in_library = 0;

            songsInBrowse.add(
                    new SongInBrowse(
                            song.getSong_id(),
                            song.getName(),
                            artist,
                            album,
                            is_in_library));        }

        model.addAttribute("artists", artists);
        model.addAttribute("albumsInBrowse", albumsInBrowse);
        model.addAttribute("songsInBrowse", songsInBrowse);

        return "main/search";
    }

    @GetMapping("/filter={filter}/albums")
    public String searchAlbums(@PathVariable String filter,
                             Model model) {
        List<Album> albums = new ArrayList<>();
        ArrayList<AlbumInBrowse> albumsInBrowse = new ArrayList<>();

        if (filter != null && !filter.isEmpty()) {
            albums = searchService.showAlbumsByPartName(filter);
            model.addAttribute("filter", filter);
        }
        else
            model.addAttribute("filter", null);

        for (Album album : albums) {
            int artist_id = album.getArtist_id();
            Artist artist = searchService.showArtist(artist_id);
            albumsInBrowse.add(new AlbumInBrowse(album, artist));
        }
        model.addAttribute("albumsInBrowse", albumsInBrowse);

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
    public String searchSongs(
            Authentication auth,
            @PathVariable String filter,
            Model model) {

        User realUser = (User)auth.getPrincipal();
        List<Song> songs = new ArrayList<>();
        ArrayList<SongInBrowse> songsInBrowse = new ArrayList<>();

        if (filter != null && !filter.isEmpty()) {
            songs = searchService.showSongsByPartName(filter);
            model.addAttribute("filter", filter);
        }
        else
            model.addAttribute("filter", null);

        for (Song song : songs) {
            int artist_id = song.getMain_artist_id();
            Artist artist = searchService.showArtist(artist_id);
            int song_id = song.getSong_id();
            Album album = searchService.showAlbumBySongId(song_id);
            boolean isInLibrary = searchService.isInLibrary(realUser.getUser_id(), song_id);
            int is_in_library;
            if (isInLibrary)
                is_in_library = 1;
            else
                is_in_library = 0;

            songsInBrowse.add(
                    new SongInBrowse(
                            song.getSong_id(),
                            song.getName(),
                            artist,
                            album,
                            is_in_library));
        }
        model.addAttribute("songsInBrowse", songsInBrowse);

        return "main/browseSongs";
    }
}