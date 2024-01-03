package ru.mirea.musicsite.dtos;

import ru.mirea.musicsite.entities.Album;
import ru.mirea.musicsite.entities.Artist;


public class SongDto {

    private int song_id;

    private String name;

    private Artist artist;

    private Album album;

    private int is_in_library;


    public SongDto(int song_id, String name, Artist artist, Album album, int is_in_library) {
        this.song_id = song_id;
        this.name = name;
        this.artist = artist;
        this.album = album;
        this.is_in_library = is_in_library;
    }

    public SongDto() {}

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

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
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
