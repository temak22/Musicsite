package ru.mirea.musicsite.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class SongInAlbum {
    private int album_id;
    private int song_id;
    private int is_lead_song;
    private int serial_number;
    private int order_in_album_number;

    public SongInAlbum(int album_id, int song_id, int is_lead_song, int serial_number, int order_in_album_number) {
        this.album_id = album_id;
        this.song_id = song_id;
        this.is_lead_song = is_lead_song;
        this.serial_number = serial_number;
        this.order_in_album_number = order_in_album_number;
    }

    public int getAlbum_id() {
        return album_id;
    }

    public void setAlbum_id(int album_id) {
        this.album_id = album_id;
    }

    public int getSong_id() {
        return song_id;
    }

    public void setSong_id(int song_id) {
        this.song_id = song_id;
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

    public int getOrder_in_album_number() {
        return order_in_album_number;
    }

    public void setOrder_in_album_number(int order_in_album_number) {
        this.order_in_album_number = order_in_album_number;
    }
}
