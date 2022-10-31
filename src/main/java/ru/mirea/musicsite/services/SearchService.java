package ru.mirea.musicsite.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mirea.musicsite.DAO.*;
import ru.mirea.musicsite.entities.*;

import java.util.List;

@Service
public class SearchService {

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

    public List<Artist> showArtistsByPartName(String partname) {
        return artistDAO.showByPartName(partname + "%", "% " + partname + "%");
    }


    public Album showAlbumBySongId(int id) {
        SongInAlbum songInAlbum = songInAlbumDAO.showBySongId(id);
        return albumDAO.show(songInAlbum.getAlbum_id());
    }

    public List<Album> showAlbumsByPartName(String partname) {
        return albumDAO.showByPartName(partname + "%", "% " + partname + "%");
    }


    public List<Song> showSongsByPartName(String partname) {
        return songDAO.showByPartName(partname + "%", "% " + partname + "%");
    }


    public int addSongInLibrary(SongInLibrary songInLibrary) {
        return songInLibraryDAO.save(songInLibrary);
    }

    public boolean isInLibrary(int user_id, int song_id) {
        SongInLibrary songInLibrary = songInLibraryDAO.show(user_id, song_id);
        return songInLibrary != null;
    }
}
