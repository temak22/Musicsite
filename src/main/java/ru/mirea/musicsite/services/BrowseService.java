package ru.mirea.musicsite.services;

import org.springframework.stereotype.Service;
import ru.mirea.musicsite.RepositoriesCollection;
import ru.mirea.musicsite.entities.*;
import ru.mirea.musicsite.security.entities.User;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

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

        int amountOfLeadSongsOnAlbum;
        if (songs.size() <= 6)
            amountOfLeadSongsOnAlbum = 1;
        else if (songs.size() <= 14)
            amountOfLeadSongsOnAlbum = 2;
        else if (songs.size() <= 19)
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

    public String findRecommendStyle(User user) {
        List<AlbumInLibrary> albumsInLibrary = albumInLibraryRepository.showByUserId(user.getUser_id());

        List<Album> albums = albumsInLibrary.stream()
                .map(albumInLibrary -> albumRepository.show(albumInLibrary.getAlbum_id()))
                .toList();

        Map<String, List<Album>> albumsPerStyle = albums.stream()
                .collect(groupingBy(Album::getStyle));

        AtomicReference<String> recommendStyle = new AtomicReference<>();
        AtomicInteger maxAlbums = new AtomicInteger();
        albumsPerStyle.forEach((style, albumsList) -> {
            if (albumsList.size() > maxAlbums.get()) {
                maxAlbums.set(albumsList.size());
                recommendStyle.set(style);
            }
        });

        return recommendStyle.get();
    }

    public List<Album> indexRecommendAlbums(User user) {
        List<AlbumInLibrary> albumsInLibrary = albumInLibraryRepository.showByUserId(user.getUser_id());

        List<Integer> albumsInLibraryIds = albumsInLibrary.stream()
                .map(AlbumInLibrary::getAlbum_id)
                .toList();

        String recommendStyle = findRecommendStyle(user);

        List<Album> albumsByRecommendStyle = albumRepository.showByStyle(recommendStyle);
        List<Album> recommendAlbums = new ArrayList<>();
        albumsByRecommendStyle.forEach(album -> {
            if (!albumsInLibraryIds.contains(album.getAlbum_id())) {
                recommendAlbums.add(album);
            }
        });

        return recommendAlbums;
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

    public void deleteSongInLibrary(SongInLibrary songInLibrary) {
        songInLibraryRepository.delete(songInLibrary.getUser_id(), songInLibrary.getSong_id());
    }

    public int addAlbumInLibrary(AlbumInLibrary albumInLibrary) {
        return albumInLibraryRepository.save(albumInLibrary);
    }

    public void deleteAlbumInLibrary(AlbumInLibrary albumInLibrary) {
        albumInLibraryRepository.delete(albumInLibrary.getUser_id(), albumInLibrary.getAlbum_id());
    }

    public boolean isSongInLibrary(int user_id, int song_id) {
        SongInLibrary songInLibrary = songInLibraryRepository.show(user_id, song_id);
        return songInLibrary != null;
    }

    public boolean isAlbumInLibrary(int user_id, int album_id) {
        AlbumInLibrary albumInLibrary = albumInLibraryRepository.show(user_id, album_id);
        return albumInLibrary != null;
    }
}
