package ru.mirea.musicsite.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.mirea.musicsite.entities.*;
import ru.mirea.musicsite.security.entities.User;
import ru.mirea.musicsite.viewEntity.AlbumInBrowse;
import ru.mirea.musicsite.services.BrowseService;
import ru.mirea.musicsite.viewEntity.SongInArtistBrowse;
import ru.mirea.musicsite.viewEntity.SongInAlbumBrowse;
import ru.mirea.musicsite.viewEntity.SongInBrowse;

import javax.script.*;
import javax.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/browse")
public class BrowseController {

    @Autowired
    private BrowseService browseService;

    private User realUser;
    private String playing_song_src;
    private String playing_song_author;
    private String playing_song_name;

    @GetMapping("")
    public String browse(Model model) {
        List<Album> albums = browseService.indexAlbum();
        ArrayList<AlbumInBrowse> albumsInBrowse = new ArrayList<>();

        for (Album album : albums) {
            int artist_id = album.getArtist_id();
            Artist artist = browseService.showArtist(artist_id);
            albumsInBrowse.add(new AlbumInBrowse(album, artist));
        }
        model.addAttribute("albumsInBrowse", albumsInBrowse);

        List<Chart> charts = browseService.indexChart();
        model.addAttribute("charts", charts);
        model.addAttribute("playing_song_src", playing_song_src);
        model.addAttribute("playing_song_author", playing_song_author);
        model.addAttribute("playing_song_name", playing_song_name);

        return "main/browse";
    }

    @GetMapping("/albums")
    public String browseAlbums(Model model) {
        List<Album> albums = browseService.indexAlbum();
        ArrayList<AlbumInBrowse> albumsInBrowse = new ArrayList<>();

        for (Album album : albums) {
            int artist_id = album.getArtist_id();
            Artist artist = browseService.showArtist(artist_id);
            albumsInBrowse.add(new AlbumInBrowse(album, artist));
        }
        model.addAttribute("albumsInBrowse", albumsInBrowse);
        model.addAttribute("playing_song_src", playing_song_src);
        model.addAttribute("playing_song_author", playing_song_author);
        model.addAttribute("playing_song_name", playing_song_name);

        return "main/browseAlbums";
    }

    @GetMapping("/albums/{id}")
    public String albumPage(
            Authentication auth,
            @PathVariable int id,
            Model model) {

        if (auth != null)
            realUser = (User)auth.getPrincipal();
        else
            realUser = null;

        List<SongInAlbum> songsInAlbum = browseService.showSongsByAlbumId(id);
        ArrayList<SongInAlbumBrowse> songsInBrowse = new ArrayList<>();

        for (SongInAlbum songInAlbum : songsInAlbum) {
            int song_id = songInAlbum.getSong_id();
            Song song = browseService.showSong(song_id);
            boolean isInLibrary;
            int is_in_library;
            if (realUser != null) {
                isInLibrary = browseService.isInLibrary(realUser.getUser_id(), song_id);
                if (isInLibrary)
                    is_in_library = 1;
                else
                    is_in_library = 0;
            }
            else
                is_in_library = 0;

            songsInBrowse.add(new SongInAlbumBrowse(
                    song_id,
                    song.getName(),
                    songInAlbum.getIs_lead_song(),
                    songInAlbum.getSerial_number(),
                    is_in_library));
        }
        model.addAttribute("songsInBrowse", songsInBrowse);

        Album album = browseService.showAlbum(id);
        int artist_id = album.getArtist_id();
        Artist artist = browseService.showArtist(artist_id);
        AlbumInBrowse albumInBrowse = new AlbumInBrowse(album, artist);

        model.addAttribute("albumInBrowse", albumInBrowse);
        model.addAttribute("playing_song_src", playing_song_src);
        model.addAttribute("playing_song_author", playing_song_author);
        model.addAttribute("playing_song_name", playing_song_name);

        return "main/browseAlbum";
    }

    @GetMapping("/charts")
    public String browseCharts(Model model) {
        List<Chart> charts = browseService.indexChart();
        model.addAttribute("charts", charts);

        return "main/browseCharts";
    }

    @GetMapping("/charts/{id}")
    public String chartPage(
            Authentication auth,
            @PathVariable int id,
            Model model) {

        if (auth != null)
            realUser = (User)auth.getPrincipal();
        else
            realUser = null;

        List<SongInChart> songsInChart = browseService.showSongsByChartId(id);
        ArrayList<SongInBrowse> songsInBrowse = new ArrayList<>();

        for (SongInChart songInChart : songsInChart) {
            int song_id = songInChart.getSong_id();
            Song song = browseService.showSong(song_id);
            Artist artist = browseService.showArtist(song.getMain_artist_id());
            Album album = browseService.showAlbumBySongId(song_id);
            boolean isInLibrary;
            int is_in_library;
            if (realUser != null) {
                isInLibrary = browseService.isInLibrary(realUser.getUser_id(), song_id);
                if (isInLibrary)
                    is_in_library = 1;
                else
                    is_in_library = 0;
            }
            else
                is_in_library = 0;

            songsInBrowse.add(new SongInBrowse(
                    song_id,
                    song.getName(),
                    artist,
                    album,
                    is_in_library));
        }
        model.addAttribute("songsInBrowse", songsInBrowse);

        Chart chart = browseService.showChart(id);
        model.addAttribute("chart", chart);
        model.addAttribute("playing_song_src", playing_song_src);
        model.addAttribute("playing_song_author", playing_song_author);
        model.addAttribute("playing_song_name", playing_song_name);

        return "main/browseChart";
    }

    @GetMapping("/artists/{id}")
    public String artistPage(
            Authentication auth,
            @PathVariable int id,
            Model model) {

        if (auth != null)
            realUser = (User)auth.getPrincipal();
        else
            realUser = null;

        Artist artist = browseService.showArtist(id);
        model.addAttribute("artist", artist);

        List<Song> songs = browseService.showSongsByArtistId(id);
        ArrayList<SongInArtistBrowse> songsInArtistBrowse = new ArrayList<>();
        for (Song song : songs) {
            int song_id = song.getSong_id();
            Album album = browseService.showAlbumBySongId(song_id);
            boolean isInLibrary;
            int is_in_library;
            if (realUser != null) {
                isInLibrary = browseService.isInLibrary(realUser.getUser_id(), song_id);
                if (isInLibrary)
                    is_in_library = 1;
                else
                    is_in_library = 0;
            }
            else
                is_in_library = 0;

            songsInArtistBrowse.add(new SongInArtistBrowse(
                    song_id,
                    song.getName(),
                    album,
                    is_in_library));
        }

        List<FeatArtist> feats = browseService.showFeatsByArtistId(id);
        for (FeatArtist feat : feats) {
            int song_id = feat.getSong_id();
            Song song = browseService.showSong(song_id);
            Album album = browseService.showAlbumBySongId(song_id);
            boolean isInLibrary;
            int is_in_library;
            if (realUser != null) {
                isInLibrary = browseService.isInLibrary(realUser.getUser_id(), song_id);
                if (isInLibrary)
                    is_in_library = 1;
                else
                    is_in_library = 0;
            }
            else
                is_in_library = 0;

            songsInArtistBrowse.add(new SongInArtistBrowse(
                    song_id,
                    song.getName(),
                    album,
                    is_in_library));
        }

        model.addAttribute("songsInArtistBrowse", songsInArtistBrowse);

        List<Album> albums = browseService.showAlbumsByArtistId(id);
        model.addAttribute("albums", albums);
        model.addAttribute("playing_song_src", playing_song_src);
        model.addAttribute("playing_song_author", playing_song_author);
        model.addAttribute("playing_song_name", playing_song_name);

        return "main/browseArtist";
    }

    @GetMapping("/artists/{id}/albums")
    public String artistPageAlbums(
            @PathVariable int id,
            Model model) {

        List<Album> albums = browseService.showAlbumsByArtistId(id);
        model.addAttribute("albums", albums);
        model.addAttribute("playing_song_src", playing_song_src);
        model.addAttribute("playing_song_author", playing_song_author);
        model.addAttribute("playing_song_name", playing_song_name);

        return "main/browseArtistAlbums";
    }

    @GetMapping("/artists/{id}/songs")
    public String artistPageSongs(
            Authentication auth,
            @PathVariable int id,
            Model model) {

        if (auth != null)
            realUser = (User)auth.getPrincipal();
        else
            realUser = null;

        List<Song> songs = browseService.showSongsByArtistId(id);
        ArrayList<SongInArtistBrowse> songsInArtistBrowse = new ArrayList<>();

        for (Song song : songs) {
            int song_id = song.getSong_id();
            Album album = browseService.showAlbumBySongId(song_id);
            boolean isInLibrary;
            int is_in_library;
            if (realUser != null) {
                isInLibrary = browseService.isInLibrary(realUser.getUser_id(), song_id);
                if (isInLibrary)
                    is_in_library = 1;
                else
                    is_in_library = 0;
            }
            else
                is_in_library = 0;

            songsInArtistBrowse.add(new SongInArtistBrowse(
                    song_id,
                    song.getName(),
                    album,
                    is_in_library));
        }

        List<FeatArtist> feats = browseService.showFeatsByArtistId(id);
        for (FeatArtist feat : feats) {
            int song_id = feat.getSong_id();
            Song song = browseService.showSong(song_id);
            Album album = browseService.showAlbumBySongId(song_id);
            boolean isInLibrary;
            int is_in_library;
            if (realUser != null) {
                isInLibrary = browseService.isInLibrary(realUser.getUser_id(), song_id);
                if (isInLibrary)
                    is_in_library = 1;
                else
                    is_in_library = 0;
            }
            else
                is_in_library = 0;

            songsInArtistBrowse.add(new SongInArtistBrowse(
                    song_id,
                    song.getName(),
                    album,
                    is_in_library));
        }
        model.addAttribute("songsInArtistBrowse", songsInArtistBrowse);
        model.addAttribute("playing_song_src", playing_song_src);
        model.addAttribute("playing_song_author", playing_song_author);
        model.addAttribute("playing_song_name", playing_song_name);

        return "main/browseArtistSongs";
    }

    @PostMapping("/addSong")
    public String addSong(
            HttpServletRequest request,
            @RequestParam int song_id,
            Authentication auth,
            Model model){

        if (auth != null)
            realUser = (User)auth.getPrincipal();
        else
            realUser = null;

        SongInLibrary songInLibrary;
        if (realUser != null)
            songInLibrary = new SongInLibrary(realUser.getUser_id(), song_id);
        else
            songInLibrary = new SongInLibrary(0, song_id);

        browseService.addSongInLibrary(songInLibrary);
        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }


    @PostMapping("/playSong")
    public String playSong(
            HttpServletRequest request,
            @RequestParam int song_id,
            Model model) throws Exception {

        Song song = browseService.showSong(song_id);
        playing_song_src = "/static/mp3/" + song.getSong_file();
        playing_song_author = browseService.showArtist(song.getMain_artist_id()).getNickname();
        playing_song_name = song.getName();


        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }
}