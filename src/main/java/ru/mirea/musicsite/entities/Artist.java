package ru.mirea.musicsite.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class Artist {
    private int artist_id;
    private String nickname;
    private String email;
    private String phone;
    private String avatar_file;
    private String page_photo_file;

    public Artist(int artist_id, String nickname, String email, String phone, String avatar_file, String page_photo_file) {
        this.artist_id = artist_id;
        this.nickname = nickname;
        this.email = email;
        this.phone = phone;
        this.avatar_file = avatar_file;
        this.page_photo_file = page_photo_file;
    }

    public int getArtist_id() {
        return artist_id;
    }

    public void setArtist_id(int artist_id) {
        this.artist_id = artist_id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAvatar_file() {
        return avatar_file;
    }

    public void setAvatar_file(String avatar_file) {
        this.avatar_file = avatar_file;
    }

    public String getPage_photo_file() {
        return page_photo_file;
    }

    public void setPage_photo_file(String page_photo_file) {
        this.page_photo_file = page_photo_file;
    }
}