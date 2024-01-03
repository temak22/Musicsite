package ru.mirea.musicsite.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mirea.musicsite.DAO.*;
import ru.mirea.musicsite.entities.*;

import java.util.List;

@Service
public class BrowseService {

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private SongRepository songRepository;

    @Autowired
    private SongInAlbumRepository songInAlbumRepository;

    @Autowired
    private ChartRepository chartRepository;

    @Autowired
    private SongInChartRepository songInChartRepository;

    @Autowired
    private FeatArtistRepository featArtistRepository;

    @Autowired
    private SongInLibraryRepository songInLibraryRepository;


    public List<Album> indexAlbum() {
        return albumRepository.index();
    }

    public Album showAlbum(int id) {
        return albumRepository.show(id);
    }

    public List<Album> showAlbumsByArtistId(int id) {
        return albumRepository.showByArtistId(id);
    }

    public Album showAlbumBySongId(int id) {
        SongInAlbum songInAlbum = songInAlbumRepository.showBySongId(id);
        return albumRepository.show(songInAlbum.getAlbum_id());
    }


    public Artist showArtist(int id) {
        return artistRepository.show(id);
    }


    public List<FeatArtist> showFeatsByArtistId(int id) {
        return featArtistRepository.showByArtistId(id);
    }


    public Song showSong(int id) {
        return songRepository.show(id);
    }

    public List<Song> showSongsByArtistId(int id) {
        return songRepository.showByArtistId(id);
    }


    public List<SongInAlbum> showSongsByAlbumId(int id) {
        return songInAlbumRepository.showByAlbumId(id);
    }


    public List<SongInChart> showSongsByChartId(int id) {
        return songInChartRepository.showByChartId(id);
    }


    public List<Chart> indexChart() {
        return chartRepository.index();
    }

    public Chart showChart(int id) {
        return chartRepository.show(id);
    }


    public int addSongInLibrary(SongInLibrary songInLibrary) {
        return songInLibraryRepository.save(songInLibrary);
    }

    public boolean isInLibrary(int user_id, int song_id) {
        SongInLibrary songInLibrary = songInLibraryRepository.show(user_id, song_id);
        return songInLibrary != null;
    }

}
