package ru.mirea.musicsite.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SongInChart {
    private int chart_id;
    private int song_id;
    private int serial_number;
}
