package ru.mirea.musicsite.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.mirea.musicsite.entities.*;
import ru.mirea.musicsite.security.entities.User;
import ru.mirea.musicsite.dtos.AlbumDto;
import ru.mirea.musicsite.services.BrowseService;
import ru.mirea.musicsite.dtos.ArtistSongDto;
import ru.mirea.musicsite.dtos.AlbumSongDto;
import ru.mirea.musicsite.dtos.SongDto;

import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


@Controller
@RequestMapping("/browse")
public class BrowseController {

    private final BrowseService browseService;

    private User currentUser;

    public BrowseController(BrowseService browseService) {
        this.browseService = browseService;
    }


    @GetMapping("")
    public String browse(Model model) {
        List<Album> albums = browseService.indexAlbum();
        model.addAttribute("albumsInBrowse", convertToAlbumDtoList(albums));

        List<Chart> charts = browseService.indexChart();
        model.addAttribute("charts", charts);

        return "main/browse";
    }

    @GetMapping("/albums")
    public String browseAlbums(Model model) {
        List<Album> albums = browseService.indexAlbum();
        model.addAttribute("albumsInBrowse", convertToAlbumDtoList(albums));

        return "main/browseAlbums";
    }

    @GetMapping("/albums/{id}")
    public String albumPage(Authentication auth,
                            @PathVariable int id,
                            Model model) {

        if (auth != null)
            currentUser = (User)auth.getPrincipal();
        else
            currentUser = null;

        browseService.refreshAlbumLeadSongs(id);

        List<SongInAlbum> songsInAlbum = browseService.showSongsByAlbumId(id);
        model.addAttribute("songsInBrowse", convertToAlbumSongDtoList(songsInAlbum));

        Album album = browseService.showAlbum(id);
        Artist artist = browseService.showArtist(album.getArtist_id());
        AlbumDto albumDto = new AlbumDto(album, artist);
        model.addAttribute("albumInBrowse", albumDto);

        return "main/browseAlbum";
    }

    @GetMapping("/charts")
    public String browseCharts(Model model) {
        List<Chart> charts = browseService.indexChart();
        model.addAttribute("charts", charts);

        return "main/browseCharts";
    }

    @GetMapping("/charts/{id}")
    public String chartPage(Authentication auth,
                            @PathVariable int id,
                            Model model) {

        if (auth != null)
            currentUser = (User)auth.getPrincipal();
        else
            currentUser = null;


        List<SongInChart> songsInChart = browseService.showSongsByChartId(id);
        model.addAttribute("songsInBrowse", convertToSongDtoList(songsInChart));

        Chart chart = browseService.showChart(id);
        model.addAttribute("chart", chart);

        return "main/browseChart";
    }

    @GetMapping("/artists/{id}")
    public String artistPage(Authentication auth,
                             @PathVariable int id,
                             Model model) {

        if (auth != null)
            currentUser = (User)auth.getPrincipal();
        else
            currentUser = null;


        Artist artist = browseService.showArtist(id);
        model.addAttribute("artist", artist);

        List<Song> songs = browseService.showSongsByArtistId(id);
        List<FeatArtist> feats = browseService.showFeatsByArtistId(id);
        model.addAttribute("songsInArtistBrowse", convertToArtistSongDtoList(songs, feats));

        List<Album> albums = browseService.showAlbumsByArtistId(id);
        model.addAttribute("albums", albums);

        return "main/browseArtist";
    }

    @GetMapping("/artists/{id}/albums")
    public String artistPageAlbums(@PathVariable int id,
                                   Model model) {

        List<Album> albums = browseService.showAlbumsByArtistId(id);
        model.addAttribute("albums", albums);

        return "main/browseArtistAlbums";
    }

    @GetMapping("/artists/{id}/songs")
    public String artistPageSongs(Authentication auth,
                                  @PathVariable int id,
                                  Model model) {

        if (auth != null)
            currentUser = (User)auth.getPrincipal();
        else
            currentUser = null;


        List<Song> songs = browseService.showSongsByArtistId(id);
        List<FeatArtist> feats = browseService.showFeatsByArtistId(id);
        model.addAttribute("songsInArtistBrowse", convertToArtistSongDtoList(songs, feats));

        return "main/browseArtistSongs";
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


    private ArrayList<ArtistSongDto> convertToArtistSongDtoList(List<Song> songs, List<FeatArtist> feats) {
        ArrayList<ArtistSongDto> songList = new ArrayList<>();

        for (Song song : songs) {
            int song_id = song.getSong_id();
            Album album = browseService.showAlbumBySongId(song_id);

            boolean isInLibrary;
            int is_in_library;
            if (currentUser != null) {
                isInLibrary = browseService.isInLibrary(currentUser.getUser_id(), song_id);
                if (isInLibrary)
                    is_in_library = 1;
                else
                    is_in_library = 0;
            }
            else
                is_in_library = 0;

            songList.add(new ArtistSongDto(
                    song_id,
                    song.getName(),
                    album,
                    is_in_library,
                    song.getListening()));
        }

        for (FeatArtist feat : feats) {
            int song_id = feat.getSong_id();
            Song song = browseService.showSong(song_id);
            Album album = browseService.showAlbumBySongId(song_id);
            boolean isInLibrary;
            int is_in_library;
            if (currentUser != null) {
                isInLibrary = browseService.isInLibrary(currentUser.getUser_id(), song_id);
                if (isInLibrary)
                    is_in_library = 1;
                else
                    is_in_library = 0;
            }
            else
                is_in_library = 0;

            songList.add(new ArtistSongDto(
                    song_id,
                    song.getName(),
                    album,
                    is_in_library,
                    song.getListening()));
        }

        songList.sort(Comparator.comparingInt(ArtistSongDto::getListening).reversed());

        return songList;
    }

    private ArrayList<SongDto> convertToSongDtoList(List<SongInChart> songsInChart) {
        ArrayList<SongDto> songList = new ArrayList<>();

        for (SongInChart songInChart : songsInChart) {
            int song_id = songInChart.getSong_id();
            Song song = browseService.showSong(song_id);

            Artist artist = browseService.showArtist(song.getMain_artist_id());
            Album album = browseService.showAlbumBySongId(song_id);

            boolean isInLibrary;
            int is_in_library;
            if (currentUser != null) {
                isInLibrary = browseService.isInLibrary(currentUser.getUser_id(), song_id);
                if (isInLibrary)
                    is_in_library = 1;
                else
                    is_in_library = 0;
            }
            else
                is_in_library = 0;

            songList.add(new SongDto(
                    song_id,
                    song.getName(),
                    artist,
                    album,
                    is_in_library));
        }

        return songList;
    }

    private ArrayList<AlbumSongDto> convertToAlbumSongDtoList(List<SongInAlbum> songsInAlbum) {
        ArrayList<AlbumSongDto> songList = new ArrayList<>();

        for (SongInAlbum songInAlbum : songsInAlbum) {
            int song_id = songInAlbum.getSong_id();
            Song song = browseService.showSong(song_id);
            boolean isInLibrary;
            int is_in_library;
            if (currentUser != null) {
                isInLibrary = browseService.isInLibrary(currentUser.getUser_id(), song_id);
                if (isInLibrary)
                    is_in_library = 1;
                else
                    is_in_library = 0;
            }
            else
                is_in_library = 0;

            songList.add(new AlbumSongDto(
                    song_id,
                    song.getName(),
                    songInAlbum.getIs_lead_song(),
                    songInAlbum.getSerial_number(),
                    is_in_library));
        }

        return songList;
    }

    private ArrayList<AlbumDto> convertToAlbumDtoList(List<Album> albums) {
        ArrayList<AlbumDto> albumList = new ArrayList<>();

        for (Album album : albums) {
            int artist_id = album.getArtist_id();
            Artist artist = browseService.showArtist(artist_id);
            albumList.add(new AlbumDto(album, artist));
        }

        return albumList;
    }
}