package ru.mirea.musicsite.dtos;


public class AlbumSongDto {

    private int song_id;

    private String name;

    private int is_lead_song;

    private int serial_number;

    private int is_in_library;


    public AlbumSongDto(int song_id, String name, int is_lead_song, int serial_number, int is_in_library) {
        this.song_id = song_id;
        this.name = name;
        this.is_lead_song = is_lead_song;
        this.serial_number = serial_number;
        this.is_in_library = is_in_library;
    }

    public AlbumSongDto() {}

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

    public int getIs_lead_song() {
        return is_lead_song;
    }

    public void setIs_lead_song(int is_lead_song) {
        this.is_lead_song = is_lead_song;
    }

    public int getSerial_number() {
        return serial_number;
    }

    public void setSerial_number(int serial_number) {
        this.serial_number = serial_number;
    }

    public int getIs_in_library() {
        return is_in_library;
    }

    public void setIs_in_library(int is_in_library) {
        this.is_in_library = is_in_library;
    }
}
