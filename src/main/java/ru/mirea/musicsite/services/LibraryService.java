package ru.mirea.musicsite.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;
import ru.mirea.musicsite.DAO.*;
import ru.mirea.musicsite.entities.*;

import java.util.List;

@Service
public class LibraryService {

    @Autowired
    private AlbumDAO albumDAO;

    @Autowired
    private ArtistDAO artistDAO;

    @Autowired
    private SongDAO songDAO;

    @Autowired
    private SongInAlbumDAO songInAlbumDAO;

    @Autowired
    private SongInLibraryDAO songInLibraryDAO;


    public Artist showArtist(int id) {
        return artistDAO.show(id);
    }


    public Song showSong(int id) {
        return songDAO.show(id);
    }


    public Album showAlbumBySongId(int id) {
        SongInAlbum songInAlbum = songInAlbumDAO.showBySongId(id);
        return albumDAO.show(songInAlbum.getAlbum_id());
    }


    public int countSongInLibraries(int id) {
        return songInLibraryDAO.countBySongId(id);
    }

    public List<SongInLibrary> showSongsInLibraryByUserId(int id) {
        return songInLibraryDAO.showByUserId(id);
    }
}
