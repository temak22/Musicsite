package ru.mirea.musicsite.viewEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.mirea.musicsite.entities.Album;
import ru.mirea.musicsite.entities.Artist;


@Setter
@Getter
@NoArgsConstructor
public class AlbumInBrowse {
    private Album album;
    private Artist artist;

    public AlbumInBrowse(Album album, Artist artist) {
        this.album = album;
        this.artist = artist;
    }

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
