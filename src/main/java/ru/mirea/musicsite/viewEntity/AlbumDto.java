package ru.mirea.musicsite.viewEntity;

import ru.mirea.musicsite.entities.Album;
import ru.mirea.musicsite.entities.Artist;


public class AlbumDto {

    private Album album;

    private Artist artist;


    public AlbumDto(Album album, Artist artist) {
        this.album = album;
        this.artist = artist;
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
}
