package ru.mirea.musicsite.entities;


public class SongInLibrary {

    private int user_id;

    private int song_id;

    public SongInLibrary(int user_id, int song_id) {
        this.user_id = user_id;
        this.song_id = song_id;
    }

    public SongInLibrary() {}

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getSong_id() {
        return song_id;
    }

    public void setSong_id(int song_id) {
        this.song_id = song_id;
    }
}
