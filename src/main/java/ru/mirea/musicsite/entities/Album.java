package ru.mirea.musicsite.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Album {
    private int album_id;
    private String name;
    private Date release_date;
    private String style;
    private int artist_id;
    private String cover_file;
}
