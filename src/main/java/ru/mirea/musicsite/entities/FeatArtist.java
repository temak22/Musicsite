package ru.mirea.musicsite.entities;


public class FeatArtist {

    private int song_id;

    private int feat_artist_id;


    public FeatArtist(int song_id, int feat_artist_id) {
        this.song_id = song_id;
        this.feat_artist_id = feat_artist_id;
    }

    public FeatArtist() {}

    public int getSong_id() {
        return song_id;
    }

    public void setSong_id(int song_id) {
        this.song_id = song_id;
    }

    public int getFeat_artist_id() {
        return feat_artist_id;
    }

    public void setFeat_artist_id(int feat_artist_id) {
        this.feat_artist_id = feat_artist_id;
    }
}
