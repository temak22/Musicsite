package ru.mirea.musicsite.dtos;

import ru.mirea.musicsite.entities.Album;
import ru.mirea.musicsite.entities.Artist;


public class AlbumDto {

    private Album album;

    private Artist artist;

    private int is_in_library;


    public AlbumDto(Album album, Artist artist, int is_in_library) {
        this.album = album;
        this.artist = artist;
        this.is_in_library = is_in_library;
    }

    public AlbumDto() {}

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public int getIs_in_library() {
        return is_in_library;
    }

    public void setIs_in_library(int is_in_library) {
        this.is_in_library = is_in_library;
    }
}
