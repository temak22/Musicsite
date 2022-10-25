package ru.mirea.musicsite.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mirea.musicsite.DAO.AlbumDAO;
import ru.mirea.musicsite.DAO.ArtistDAO;
import ru.mirea.musicsite.DAO.SongDAO;
import ru.mirea.musicsite.DAO.SongInAlbumDAO;
import ru.mirea.musicsite.entities.Album;
import ru.mirea.musicsite.entities.Artist;
import ru.mirea.musicsite.entities.Song;
import ru.mirea.musicsite.entities.SongInAlbum;

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
}
