package ru.mirea.musicsite.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.mirea.musicsite.entities.Album;
import ru.mirea.musicsite.entities.Artist;
import ru.mirea.musicsite.entities.Song;
import ru.mirea.musicsite.security.entities.User;
import ru.mirea.musicsite.services.BrowseService;
import ru.mirea.musicsite.services.SearchService;
import ru.mirea.musicsite.dtos.AlbumDto;
import ru.mirea.musicsite.dtos.SongDto;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/search")
public class SearchController {

    private final SearchService searchService;
    private final BrowseService browseService;

    private User currentUser;

    private String playing_song_src;
    private String playing_song_author;
    private String playing_song_name;

    public SearchController(SearchService searchService, BrowseService browseService) {
        this.searchService = searchService;
        this.browseService = browseService;
    }


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
        model.addAttribute("albumsInBrowse", convertToAlbumDtoList(albums));
        model.addAttribute("songsInBrowse", convertToSongDtoList(songs));

        model.addAttribute("playing_song_src", playing_song_src);
        model.addAttribute("playing_song_author", playing_song_author);
        model.addAttribute("playing_song_name", playing_song_name);

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

        model.addAttribute("albumsInBrowse", convertToAlbumDtoList(albums));

        model.addAttribute("playing_song_src", playing_song_src);
        model.addAttribute("playing_song_author", playing_song_author);
        model.addAttribute("playing_song_name", playing_song_name);

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

        model.addAttribute("playing_song_src", playing_song_src);
        model.addAttribute("playing_song_author", playing_song_author);
        model.addAttribute("playing_song_name", playing_song_name);

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

        model.addAttribute("songsInBrowse", convertToSongDtoList(songs));

        model.addAttribute("playing_song_src", playing_song_src);
        model.addAttribute("playing_song_author", playing_song_author);
        model.addAttribute("playing_song_name", playing_song_name);

        return "main/browseSongs";
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


    private ArrayList<SongDto> convertToSongDtoList(List<Song> songs) {
        ArrayList<SongDto> songList = new ArrayList<>();

        for (Song song : songs) {
            int artist_id = song.getMain_artist_id();
            Artist artist = searchService.showArtist(artist_id);

            int song_id = song.getSong_id();
            Album album = searchService.showAlbumBySongId(song_id);

            boolean isInLibrary;
            int is_in_library;
            if (currentUser != null) {
                isInLibrary = searchService.isInLibrary(currentUser.getUser_id(), song_id);
                if (isInLibrary)
                    is_in_library = 1;
                else
                    is_in_library = 0;
            }
            else
                is_in_library = 0;

            songList.add(
                    new SongDto(
                            song.getSong_id(),
                            song.getName(),
                            artist,
                            album,
                            is_in_library
                    )
            );
        }

        return songList;
    }

    private ArrayList<AlbumDto> convertToAlbumDtoList(List<Album> albums) {
        ArrayList<AlbumDto> albumList = new ArrayList<>();

        for (Album album : albums) {
            int artist_id = album.getArtist_id();
            Artist artist = searchService.showArtist(artist_id);
            albumList.add(new AlbumDto(album, artist));
        }

        return albumList;
    }
}