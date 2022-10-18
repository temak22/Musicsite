package ru.mirea.musicsite.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.mirea.musicsite.DAO.AlbumDAO;
import ru.mirea.musicsite.DAO.ArtistDAO;
import ru.mirea.musicsite.DAO.SongDAO;
import ru.mirea.musicsite.DAO.SongInAlbumDAO;
import ru.mirea.musicsite.entities.Album;
import ru.mirea.musicsite.entities.Artist;
import ru.mirea.musicsite.entities.Song;
import ru.mirea.musicsite.entities.SongInAlbum;
import ru.mirea.musicsite.security.entities.User;
import ru.mirea.musicsite.security.repositories.UserRepo;

import java.util.List;

@Service
public class AdminService implements UserDetailsService {

    @Autowired
    private AlbumDAO albumDAO;

    @Autowired
    private ArtistDAO artistDAO;

    @Autowired
    private SongDAO songDAO;

    @Autowired
    private SongInAlbumDAO songInAlbumDAO;

    @Autowired
    private UserRepo userRepo;

    public List<Album> indexAlbum() {
        return albumDAO.index();
    }

    public int saveAlbum(Album album) {
        return albumDAO.save(album);
    }

    public boolean checkIfArtistExist(int id) {
        if (artistDAO.show(id) == null)
            return false;
        return true;
    }

    public int saveArtist(Artist artist) {
        return artistDAO.save(artist);
    }

    public void updateArtist(int id, Artist artist) {
        artistDAO.update(id, artist);
    }

    public int saveSong(Song song) {
        return songDAO.save(song);
    }

    public int saveSongInAlbum(SongInAlbum songInAlbum) {
        return songInAlbumDAO.save(songInAlbum);
    }

    public User getUserById(int id) {
        return userRepo.findById(id);
    }

    public void saveUser(User user) {
        userRepo.save(user);
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username);
    }
}
