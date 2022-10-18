package ru.mirea.musicsite.viewEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.mirea.musicsite.entities.Album;
import ru.mirea.musicsite.entities.Artist;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AlbumInBrowse {
    private Album album;
    private Artist artist;
}
