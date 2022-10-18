package ru.mirea.musicsite.viewEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.mirea.musicsite.entities.Album;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SongInArtistBrowse {
    private int song_id;
    private String name;
    private Album album;
}
