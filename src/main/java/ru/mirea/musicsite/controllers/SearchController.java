package ru.mirea.musicsite.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.mirea.musicsite.entities.Album;
import ru.mirea.musicsite.entities.Artist;
import ru.mirea.musicsite.entities.Song;
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
    public String searchForm(@RequestParam(required = false, defaultValue = "") String filter,
                             Model model) {
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
            songsInBrowse.add(new SongInBrowse(song.getSong_id(), song.getName(), artist, album));
        }

        model.addAttribute("artists", artists);
        model.addAttribute("albumsInBrowse", albumsInBrowse);
        model.addAttribute("songsInBrowse", songsInBrowse);

        return "search";
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

        return "browseAlbums";
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
        return "browseArtists";
    }

    @GetMapping("/filter={filter}/songs")
    public String searchSongs(@PathVariable String filter,
                               Model model) {
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
            songsInBrowse.add(new SongInBrowse(song.getSong_id(), song.getName(), artist, album));
        }
        model.addAttribute("songsInBrowse", songsInBrowse);

        return "browseSongs";
    }
}