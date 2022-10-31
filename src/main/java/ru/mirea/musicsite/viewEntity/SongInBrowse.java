package ru.mirea.musicsite.viewEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.mirea.musicsite.entities.Album;
import ru.mirea.musicsite.entities.Artist;

import java.util.List;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SongInBrowse {
    private int song_id;
    private String name;
    private Artist artist;
    private Album album;
    private int is_in_library;
}
