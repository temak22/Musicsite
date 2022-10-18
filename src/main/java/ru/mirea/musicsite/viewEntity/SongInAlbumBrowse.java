package ru.mirea.musicsite.viewEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SongInAlbumBrowse {
    private int song_id;
    private String name;
    private int is_lead_song;
    private int serial_number;
}
