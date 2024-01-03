package ru.mirea.musicsite.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mirea.musicsite.DAO.*;
import ru.mirea.musicsite.entities.*;

import java.util.List;

@Service
public class SearchService {

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private SongRepository songRepository;

    @Autowired
    private SongInAlbumRepository songInAlbumRepository;

    @Autowired
    private SongInLibraryRepository songInLibraryRepository;



    public Artist showArtist(int id) {
        return artistRepository.show(id);
    }

    public List<Artist> showArtistsByPartName(String partname) {
        return artistRepository.showByPartName(partname + "%", "% " + partname + "%");
    }


    public Album showAlbumBySongId(int id) {
        SongInAlbum songInAlbum = songInAlbumRepository.showBySongId(id);
        return albumRepository.show(songInAlbum.getAlbum_id());
    }

    public List<Album> showAlbumsByPartName(String partname) {
        return albumRepository.showByPartName(partname + "%", "% " + partname + "%");
    }


    public List<Song> showSongsByPartName(String partname) {
        return songRepository.showByPartName(partname + "%", "% " + partname + "%");
    }


    public int addSongInLibrary(SongInLibrary songInLibrary) {
        return songInLibraryRepository.save(songInLibrary);
    }

    public boolean isInLibrary(int user_id, int song_id) {
        SongInLibrary songInLibrary = songInLibraryRepository.show(user_id, song_id);
        return songInLibrary != null;
    }
}
