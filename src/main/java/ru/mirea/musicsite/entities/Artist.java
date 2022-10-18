package ru.mirea.musicsite.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Artist {
    private int artist_id;
    private String nickname;
    private String email;
    private String phone;
    private String avatar_file;
    private String page_photo_file;
}