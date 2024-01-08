package ru.mirea.musicsite.entities;


public class AlbumInLibrary {

    private int user_id;

    private int album_id;

    public AlbumInLibrary(int user_id, int album_id) {
        this.user_id = user_id;
        this.album_id = album_id;
    }

    public AlbumInLibrary() {}

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getAlbum_id() {
        return album_id;
    }

    public void setAlbum_id(int album_id) {
        this.album_id = album_id;
    }
}
