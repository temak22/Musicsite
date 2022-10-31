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

    @Autowired
    private FeatArtistDAO featArtistDAO;

    @Autowired
    private SongInLibraryDAO songInLibraryDAO;


    public List<Album> indexAlbum() {
        return albumDAO.index();
    }

    public Album showAlbum(int id) {
        return albumDAO.show(id);
    }

    public List<Album> showAlbumsByArtistId(int id) {
        return albumDAO.showByArtistId(id);
    }

    public Album showAlbumBySongId(int id) {
        SongInAlbum songInAlbum = songInAlbumDAO.showBySongId(id);
        return albumDAO.show(songInAlbum.getAlbum_id());
    }


    public Artist showArtist(int id) {
        return artistDAO.show(id);
    }


    public List<FeatArtist> showFeatsByArtistId(int id) {
        return featArtistDAO.showByArtistId(id);
    }


    public Song showSong(int id) {
        return songDAO.show(id);
    }

    public List<Song> showSongsByArtistId(int id) {
        return songDAO.showByArtistId(id);
    }


    public List<SongInAlbum> showSongsByAlbumId(int id) {
        return songInAlbumDAO.showByAlbumId(id);
    }


    public List<SongInChart> showSongsByChartId(int id) {
        return songInChartDAO.showByChartId(id);
    }


    public List<Chart> indexChart() {
        return chartDAO.index();
    }

    public Chart showChart(int id) {
        return chartDAO.show(id);
    }


    public int addSongInLibrary(SongInLibrary songInLibrary) {
        return songInLibraryDAO.save(songInLibrary);
    }

    public boolean isInLibrary(int user_id, int song_id) {
        SongInLibrary songInLibrary = songInLibraryDAO.show(user_id, song_id);
        return songInLibrary != null;
    }

}
