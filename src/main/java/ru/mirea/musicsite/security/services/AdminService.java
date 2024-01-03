package ru.mirea.musicsite.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.mirea.musicsite.repositories.*;
import ru.mirea.musicsite.entities.*;
import ru.mirea.musicsite.security.entities.User;
import ru.mirea.musicsite.security.repositories.UserRepo;

import java.util.List;

@Service
public class AdminService implements UserDetailsService {

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
    private UserRepo userRepo;


    public List<Album> indexAlbum() {
        return albumRepository.index();
    }

    public int saveAlbum(Album album) {
        return albumRepository.save(album);
    }

    public void updateAlbum(Album album) {
        albumRepository.update(album.getAlbum_id(), album);
    }

    public void deleteAlbum(int id) {
        albumRepository.delete(id);
    }


    public boolean checkIfArtistExist(int id) {
        return artistRepository.show(id) != null;
    }

    public int saveArtist(Artist artist) {
        return artistRepository.save(artist);
    }

    public void updateArtist(int id, Artist artist) {
        artistRepository.update(id, artist);
    }


    public int saveFeatArtist(FeatArtist featArtist) {
        return featArtistRepository.save(featArtist);
    }

    public void deleteFeatArtists(int song_id) {
        featArtistRepository.delete(song_id);
    }


    public boolean checkIfSongExist(int id) {
        return songRepository.show(id) != null;
    }

    public int saveSong(Song song) {
        return songRepository.save(song);
    }

    public void updateSong(int id, Song song) {
        songRepository.update(id, song);
    }

    public int getArtistIdBySongId(int id) {
        return songRepository.show(id).getMain_artist_id();
    }

    public void deleteSong(int id) {
        songRepository.delete(id);
    }


    public int saveSongInAlbum(SongInAlbum songInAlbum) {
        return songInAlbumRepository.save(songInAlbum);
    }

    public void updateSongInAlbum(int album_id, int song_id, SongInAlbum songInAlbum) {
        songInAlbumRepository.update(album_id, song_id, songInAlbum);
    }

    public List<SongInAlbum> showSongsByAlbumId(int id) {
        return songInAlbumRepository.showByAlbumId(id);
    }

    public int getAlbumIdBySongId(int id) {
        return songInAlbumRepository.showBySongId(id).getAlbum_id();
    }


    public int saveSongInChart(SongInChart songInChart) {
        return songInChartRepository.save(songInChart);
    }

    public void deleteSongInChart(int chart_id, int song_id) {
        songInChartRepository.delete(chart_id, song_id);
    }

    public List<SongInChart> showSongsByChartId(int id) {
        return songInChartRepository.showByChartId(id);
    }


    public List<Chart> indexChart() {
        return chartRepository.index();
    }

    public int saveChart(Chart chart) {
        return chartRepository.save(chart);
    }

    public void updateChart(Chart chart) {
        chartRepository.update(chart.getChart_id(), chart);
    }

    public void deleteChart(int id) {
        chartRepository.delete(id);
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
