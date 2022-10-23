package ru.mirea.musicsite.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mirea.musicsite.DAO.*;
import ru.mirea.musicsite.entities.*;

import java.util.List;

@Service
public class BrowseService {

    @Autowired
    private AlbumDAO albumDAO;

    @Autowired
    private ArtistDAO artistDAO;

    @Autowired
    private SongDAO songDAO;

    @Autowired
    private SongInAlbumDAO songInAlbumDAO;

    @Autowired
    private ChartDAO chartDAO;

    @Autowired
    private SongInChartDAO songInChartDAO;


    public List<Album> indexAlbum() {
        return albumDAO.index();
    }

    public Artist showArtist(int id) {
        return artistDAO.show(id);
    }

    public List<SongInAlbum> showSongsByAlbumId(int id) {
        return songInAlbumDAO.showByAlbumId(id);
    }

    public Song showSong(int id) {
        return songDAO.show(id);
    }

    public Album showAlbum(int id) {
        return albumDAO.show(id);
    }

    public Album showAlbumBySongId(int id) {
        SongInAlbum songInAlbum = songInAlbumDAO.showBySongId(id);
        return albumDAO.show(songInAlbum.getAlbum_id());
    }

    public List<Song> showSongsByArtistId(int id) {
        return songDAO.showByArtistId(id);
    }

    public List<Album> showAlbumsByArtistId(int id) {
        return albumDAO.showByArtistId(id);
    }

    public List<Chart> indexChart() {
        return chartDAO.index();
    }

    public List<SongInChart> showSongsByChartId(int id) {
        return songInChartDAO.showByChartId(id);
    }

    public Chart showChart(int id) {
        return chartDAO.show(id);
    }

}
