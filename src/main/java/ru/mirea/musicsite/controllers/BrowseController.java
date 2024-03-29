package ru.mirea.musicsite.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.mirea.musicsite.DtoConverter;
import ru.mirea.musicsite.dtos.AlbumDto;
import ru.mirea.musicsite.entities.*;
import ru.mirea.musicsite.security.entities.User;
import ru.mirea.musicsite.services.BrowseService;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/browse")
public class BrowseController {

    @Autowired
    private BrowseService browseService;

    @Autowired
    private DtoConverter converter;

    private User currentUser;


    @GetMapping("")
    public String browse(Authentication auth,
                         Model model) {

        if (auth != null)
            currentUser = (User)auth.getPrincipal();
        else
            currentUser = null;


        List<Chart> charts = browseService.indexChart();
        model.addAttribute("charts", charts);

        if (currentUser != null) {
            String recommendStyle = browseService.findRecommendStyle(currentUser);
            model.addAttribute("recommendStyle", recommendStyle);

            List<Album> recommendAlbums = browseService.indexRecommendAlbums(currentUser);
            model.addAttribute("recommendAlbums", converter.convertAlbumsToAlbumDtoList(recommendAlbums));
        } else
            model.addAttribute("recommendAlbums", new ArrayList<>());

        return "main/browse";
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
        model.addAttribute("songsInBrowse", converter.convertSongsInAlbumToAlbumSongDtoList(songsInAlbum, currentUser));

        Album album = browseService.showAlbum(id);
        Artist artist = browseService.showArtist(album.getArtist_id());

        boolean isInLibrary = browseService.isAlbumInLibrary(currentUser.getUser_id(), id);
        int is_in_library;
        if (isInLibrary)
            is_in_library = 1;
        else
            is_in_library = 0;

        AlbumDto albumDto = new AlbumDto(album, artist, is_in_library);
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
        model.addAttribute("songsInBrowse", converter.convertSongsInChartToSongDtoList(songsInChart, currentUser));

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
        model.addAttribute("songsInArtistBrowse", converter.convertSongsAndFeatsToArtistSongDtoList(songs, feats, currentUser));

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
        model.addAttribute("songsInArtistBrowse", converter.convertSongsAndFeatsToArtistSongDtoList(songs, feats, currentUser));

        return "main/browseArtistSongs";
    }
}