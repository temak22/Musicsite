package ru.mirea.musicsite.entities;


public class Song {

    private int song_id;

    private String name;

    private int main_artist_id;

    private String song_file;


    public Song(int song_id, String name, int main_artist_id, String song_file) {
        this.song_id = song_id;
        this.name = name;
        this.main_artist_id = main_artist_id;
        this.song_file = song_file;
    }

    public Song() {
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

    public int getMain_artist_id() {
        return main_artist_id;
    }

    public void setMain_artist_id(int main_artist_id) {
        this.main_artist_id = main_artist_id;
    }

    public String getSong_file() {
        return song_file;
    }

    public void setSong_file(String song_file) {
        this.song_file = song_file;
    }
}
