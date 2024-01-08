package ru.mirea.musicsite.services;

import org.springframework.stereotype.Service;
import ru.mirea.musicsite.RepositoriesCollection;
import ru.mirea.musicsite.entities.*;

import java.util.List;

@Service
public class LibraryService extends RepositoriesCollection {

    public Artist showArtist(int id) {
        return artistRepository.show(id);
    }


    public Song showSong(int id) {
        return songRepository.show(id);
    }


    public Album showAlbum(int id) {
        return albumRepository.show(id);
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

    public List<AlbumInLibrary> showAlbumsInLibraryByUserId(int id) {
        return albumInLibraryRepository.showByUserId(id);
    }
}
