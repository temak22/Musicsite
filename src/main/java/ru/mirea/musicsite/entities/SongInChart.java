package ru.mirea.musicsite.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class SongInChart {
    private int chart_id;
    private int song_id;
    private int serial_number;

    public SongInChart(int chart_id, int song_id, int serial_number) {
        this.chart_id = chart_id;
        this.song_id = song_id;
        this.serial_number = serial_number;
    }

    public int getChart_id() {
        return chart_id;
    }

    public void setChart_id(int chart_id) {
        this.chart_id = chart_id;
    }

    public int getSong_id() {
        return song_id;
    }

    public void setSong_id(int song_id) {
        this.song_id = song_id;
    }

    public int getSerial_number() {
        return serial_number;
    }

    public void setSerial_number(int serial_number) {
        this.serial_number = serial_number;
    }
}
