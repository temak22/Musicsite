package ru.mirea.musicsite.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Song {
    private int song_id;
    private String name;
    private int main_artist_id;
    private String song_file;
}
