package ru.mirea.musicsite.services;

import org.springframework.stereotype.Service;
import ru.mirea.musicsite.RepositoriesCollection;
import ru.mirea.musicsite.dtos.ArtistSongDto;
import ru.mirea.musicsite.entities.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class BrowseService extends RepositoriesCollection {

    public List<Album> indexAlbum() {
        return albumRepository.index();
    }

    public Album showAlbum(int id) {
        return albumRepository.show(id);
    }

    public List<Album> showAlbumsByArtistId(int id) {
        return albumRepository.showByArtistId(id);
    }

    public Album showAlbumBySongId(int id) {
        SongInAlbum songInAlbum = songInAlbumRepository.showBySongId(id);
        return albumRepository.show(songInAlbum.getAlbum_id());
    }

    public void refreshAlbumLeadSongs(int id) {
        List<SongInAlbum> albumSongs = songInAlbumRepository.showByAlbumId(id);

        List<Song> songs = albumSongs.stream()
                .map(songInAlbum -> songRepository.show(songInAlbum.getSong_id()))
                .sorted(Comparator.comparingInt(Song::getListening).reversed())
                .toList();

        int amountOfLeadSongsOnAlbum = 1;
        if (songs.size() > 6 & songs.size() <= 14)
            amountOfLeadSongsOnAlbum = 2;
        else if (songs.size() > 14 & songs.size() <= 19)
            amountOfLeadSongsOnAlbum = 3;
        else
            amountOfLeadSongsOnAlbum = 4;

        songs.forEach(song -> {
            SongInAlbum songInAlbum = songInAlbumRepository.showBySongId(song.getSong_id());
            songInAlbum.setIs_lead_song(0);
            songInAlbumRepository.update(id, song.getSong_id(), songInAlbum);
        });

        songs.stream().limit(amountOfLeadSongsOnAlbum).forEach(song -> {
            SongInAlbum songInAlbum = songInAlbumRepository.showBySongId(song.getSong_id());
            songInAlbum.setIs_lead_song(1);
            songInAlbumRepository.update(id, song.getSong_id(), songInAlbum);
        });
    }


    public Artist showArtist(int id) {
        return artistRepository.show(id);
    }


    public List<FeatArtist> showFeatsByArtistId(int id) {
        return featArtistRepository.showByArtistId(id);
    }


    public Song showSong(int id) {
        return songRepository.show(id);
    }

    public List<Song> showSongsByArtistId(int id) {
        return songRepository.showByArtistId(id);
    }


    public List<SongInAlbum> showSongsByAlbumId(int id) {
        return songInAlbumRepository.showByAlbumId(id);
    }


    public List<SongInChart> showSongsByChartId(int id) {
        return songInChartRepository.showByChartId(id);
    }


    public List<Chart> indexChart() {
        return chartRepository.index();
    }

    public Chart showChart(int id) {
        return chartRepository.show(id);
    }


    public int addSongInLibrary(SongInLibrary songInLibrary) {
        return songInLibraryRepository.save(songInLibrary);
    }

    public boolean isInLibrary(int user_id, int song_id) {
        SongInLibrary songInLibrary = songInLibraryRepository.show(user_id, song_id);
        return songInLibrary != null;
    }

}
