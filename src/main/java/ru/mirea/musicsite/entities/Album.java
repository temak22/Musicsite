package ru.mirea.musicsite.entities;

import java.sql.Date;


public class Album {

    private int album_id;

    private String name;

    private Date release_date;

    private String style;

    private int artist_id;

    private String cover_file;


    public Album(int album_id, String name, Date release_date, String style, int artist_id, String cover_file) {
        this.album_id = album_id;
        this.name = name;
        this.release_date = release_date;
        this.style = style;
        this.artist_id = artist_id;
        this.cover_file = cover_file;
    }

    public Album() {}

    public int getAlbum_id() {
        return album_id;
    }

    public void setAlbum_id(int album_id) {
        this.album_id = album_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getRelease_date() {
        return release_date;
    }

    public void setRelease_date(Date release_date) {
        this.release_date = release_date;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public int getArtist_id() {
        return artist_id;
    }

    public void setArtist_id(int artist_id) {
        this.artist_id = artist_id;
    }

    public String getCover_file() {
        return cover_file;
    }

    public void setCover_file(String cover_file) {
        this.cover_file = cover_file;
    }
}
