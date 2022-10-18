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

    public List<Album> showAlbumsByPartName(String partname) {
        return albumDAO.showByPartName(partname + "%", "% " + partname + "%");
    }

    public List<Artist> showArtistsByPartName(String partname) {
        return artistDAO.showByPartName(partname + "%", "% " + partname + "%");
    }

    public List<Song> showSongsByPartName(String partname) {
        return songDAO.showByPartName(partname + "%", "% " + partname + "%");
    }
}
