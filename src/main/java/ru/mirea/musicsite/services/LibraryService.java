package ru.mirea.musicsite.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mirea.musicsite.DAO.*;
import ru.mirea.musicsite.entities.*;

import java.util.List;

@Service
public class LibraryService {

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


    public Song showSong(int id) {
        return songRepository.show(id);
    }


    public Album showAlbumBySongId(int id) {
        SongInAlbum songInAlbum = songInAlbumRepository.showBySongId(id);
        return albumRepository.show(songInAlbum.getAlbum_id());
    }


    public int countSongInLibraries(int id) {
        return songInLibraryRepository.countBySongIdInAllLibraries(id);
    }

    public List<SongInLibrary> showSongsInLibraryByUserId(int id) {
        return songInLibraryRepository.showByUserId(id);
    }
}
