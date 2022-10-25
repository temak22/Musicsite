package ru.mirea.musicsite.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.mirea.musicsite.DAO.*;
import ru.mirea.musicsite.entities.*;
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
    private ChartDAO chartDAO;

    @Autowired
    private SongInChartDAO songInChartDAO;

    @Autowired
    private FeatArtistDAO featArtistDAO;

    @Autowired
    private UserRepo userRepo;


    public List<Album> indexAlbum() {
        return albumDAO.index();
    }

    public int saveAlbum(Album album) {
        return albumDAO.save(album);
    }

    public void updateAlbum(Album album) {
        albumDAO.update(album.getAlbum_id(), album);
    }

    public void deleteAlbum(int id) {
        albumDAO.delete(id);
    }


    public boolean checkIfArtistExist(int id) {
        return artistDAO.show(id) != null;
    }

    public int saveArtist(Artist artist) {
        return artistDAO.save(artist);
    }

    public void updateArtist(int id, Artist artist) {
        artistDAO.update(id, artist);
    }


    public int saveFeatArtist(FeatArtist featArtist) {
        return featArtistDAO.save(featArtist);
    }


    public boolean checkIfSongExist(int id) {
        return songDAO.show(id) != null;
    }

    public int saveSong(Song song) {
        return songDAO.save(song);
    }

    public void deleteSong(int id) {
        songDAO.delete(id);
    }


    public int saveSongInAlbum(SongInAlbum songInAlbum) {
        return songInAlbumDAO.save(songInAlbum);
    }

    public List<SongInAlbum> showSongsByAlbumId(int id) {
        return songInAlbumDAO.showByAlbumId(id);
    }


    public int saveSongInChart(SongInChart songInChart) {
        return songInChartDAO.save(songInChart);
    }

    public void deleteSongInChart(int chart_id, int song_id) {
        songInChartDAO.delete(chart_id, song_id);
    }

    public List<SongInChart> showSongsByChartId(int id) {
        return songInChartDAO.showByChartId(id);
    }


    public List<Chart> indexChart() {
        return chartDAO.index();
    }

    public int saveChart(Chart chart) {
        return chartDAO.save(chart);
    }

    public void updateChart(Chart chart) {
        chartDAO.update(chart.getChart_id(), chart);
    }

    public void deleteChart(int id) {
        chartDAO.delete(id);
    }


    public void saveUser(User user) {
        userRepo.save(user);
    }

    public User getUserById(int id) {
        return userRepo.findById(id);
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username);
    }
}
