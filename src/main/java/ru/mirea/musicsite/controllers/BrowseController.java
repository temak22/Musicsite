package ru.mirea.musicsite.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.mirea.musicsite.DAO.FeatArtistDAO;
import ru.mirea.musicsite.entities.*;
import ru.mirea.musicsite.viewEntity.AlbumInBrowse;
import ru.mirea.musicsite.services.BrowseService;
import ru.mirea.musicsite.viewEntity.SongInArtistBrowse;
import ru.mirea.musicsite.viewEntity.SongInAlbumBrowse;
import ru.mirea.musicsite.viewEntity.SongInBrowse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/browse")
public class BrowseController {

    @Autowired
    private BrowseService browseService;

    @GetMapping("")
    public String browse(Map<String, Object> model) {
        List<Album> albums = browseService.indexAlbum();
        ArrayList<AlbumInBrowse> albumsInBrowse = new ArrayList<>();

        for (Album album : albums) {
            int artist_id = album.getArtist_id();
            Artist artist = browseService.showArtist(artist_id);
            albumsInBrowse.add(new AlbumInBrowse(album, artist));
        }
        model.put("albumsInBrowse", albumsInBrowse);

        List<Chart> charts = browseService.indexChart();
        model.put("charts", charts);

        return "main/browse";
    }

    @GetMapping("/albums")
    public String browseAlbums(Map<String, Object> model) {
        List<Album> albums = browseService.indexAlbum();
        ArrayList<AlbumInBrowse> albumsInBrowse = new ArrayList<>();

        for (Album album : albums) {
            int artist_id = album.getArtist_id();
            Artist artist = browseService.showArtist(artist_id);
            albumsInBrowse.add(new AlbumInBrowse(album, artist));
        }
        model.put("albumsInBrowse", albumsInBrowse);

        return "main/browseAlbums";
    }

    @GetMapping("/albums/{id}")
    public String albumPage(@PathVariable int id, Map<String, Object> model) {
        List<SongInAlbum> songsInAlbum = browseService.showSongsByAlbumId(id);
        ArrayList<SongInAlbumBrowse> songsInBrowse = new ArrayList<>();

        for (SongInAlbum songInAlbum : songsInAlbum) {
            int song_id = songInAlbum.getSong_id();
            Song song = browseService.showSong(song_id);

            songsInBrowse.add(new SongInAlbumBrowse(
                    song_id,
                    song.getName(),
                    songInAlbum.getIs_lead_song(),
                    songInAlbum.getSerial_number()));
        }
        model.put("songsInBrowse", songsInBrowse);

        Album album = browseService.showAlbum(id);
        int artist_id = album.getArtist_id();
        Artist artist = browseService.showArtist(artist_id);
        AlbumInBrowse albumInBrowse = new AlbumInBrowse(album, artist);

        model.put("albumInBrowse", albumInBrowse);

        return "main/browseAlbum";
    }

    @GetMapping("/charts")
    public String browseCharts(Map<String, Object> model) {
        List<Chart> charts = browseService.indexChart();
        model.put("charts", charts);

        return "main/browseCharts";
    }

    @GetMapping("/charts/{id}")
    public String chartPage(@PathVariable int id, Map<String, Object> model) {
        List<SongInChart> songsInChart = browseService.showSongsByChartId(id);
        ArrayList<SongInBrowse> songsInBrowse = new ArrayList<>();

        for (SongInChart songInChart : songsInChart) {
            int song_id = songInChart.getSong_id();
            Song song = browseService.showSong(song_id);
            Artist artist = browseService.showArtist(song.getMain_artist_id());
            Album album = browseService.showAlbumBySongId(song_id);
            songsInBrowse.add(new SongInBrowse(
                    song_id,
                    song.getName(),
                    artist,
                    album));
        }
        model.put("songsInBrowse", songsInBrowse);

        Chart chart = browseService.showChart(id);
        model.put("chart", chart);

        return "main/browseChart";
    }

    @GetMapping("/artists/{id}")
    public String artistPage(@PathVariable int id, Map<String, Object> model) {
        Artist artist = browseService.showArtist(id);
        model.put("artist", artist);

        List<Song> songs = browseService.showSongsByArtistId(id);
        ArrayList<SongInArtistBrowse> songsInArtistBrowse = new ArrayList<>();
        for (Song song : songs) {
            int song_id = song.getSong_id();
            Album album = browseService.showAlbumBySongId(song_id);

            songsInArtistBrowse.add(new SongInArtistBrowse(
                    song_id,
                    song.getName(),
                    album));
        }

        List<FeatArtist> feats = browseService.showFeatsByArtistId(id);
        for (FeatArtist feat : feats) {
            int song_id = feat.getSong_id();
            Song song = browseService.showSong(song_id);
            Album album = browseService.showAlbumBySongId(song_id);

            songsInArtistBrowse.add(new SongInArtistBrowse(
                    song_id,
                    song.getName(),
                    album));
        }

        model.put("songsInArtistBrowse", songsInArtistBrowse);

        List<Album> albums = browseService.showAlbumsByArtistId(id);
        model.put("albums", albums);

        return "main/browseArtist";
    }

    @GetMapping("/artists/{id}/albums")
    public String artistPageAlbums(@PathVariable int id, Map<String, Object> model) {
        List<Album> albums = browseService.showAlbumsByArtistId(id);
        model.put("albums", albums);

        return "main/browseArtistAlbums";
    }

    @GetMapping("/artists/{id}/songs")
    public String artistPageSongs(@PathVariable int id, Map<String, Object> model) {
        List<Song> songs = browseService.showSongsByArtistId(id);
        ArrayList<SongInArtistBrowse> songsInArtistBrowse = new ArrayList<>();

        for (Song song : songs) {
            int song_id = song.getSong_id();
            Album album = browseService.showAlbumBySongId(song_id);
            songsInArtistBrowse.add(new SongInArtistBrowse(
                    song_id,
                    song.getName(),
                    album));
        }
        model.put("songsInArtistBrowse", songsInArtistBrowse);

        return "main/browseArtistSongs";
    }
}