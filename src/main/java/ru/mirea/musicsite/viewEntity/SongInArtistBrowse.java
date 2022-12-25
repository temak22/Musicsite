package ru.mirea.musicsite.viewEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.mirea.musicsite.entities.Album;
import ru.mirea.musicsite.entities.Artist;

import java.util.List;


@Setter
@Getter
@NoArgsConstructor
public class SongInArtistBrowse {
    private int song_id;
    private String name;
    private Album album;
    private int is_in_library;

    public SongInArtistBrowse(int song_id, String name, Album album, int is_in_library) {
        this.song_id = song_id;
        this.name = name;
        this.album = album;
        this.is_in_library = is_in_library;
    }

    public int getSong_id() {
        return song_id;
    }

    public void setSong_id(int song_id) {
        this.song_id = song_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public int getIs_in_library() {
        return is_in_library;
    }

    public void setIs_in_library(int is_in_library) {
        this.is_in_library = is_in_library;
    }
}
