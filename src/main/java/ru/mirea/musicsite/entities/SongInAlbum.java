package ru.mirea.musicsite.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SongInAlbum {
    private int album_id;
    private int song_id;
    private int is_lead_song;
    private int serial_number;
}
