package ru.mirea.musicsite.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.mirea.musicsite.entities.Song;

import java.util.List;

@Repository
public class SongRepository {

    public final JdbcTemplate jdbcTemplate;

    @Autowired
    public SongRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public int count() {
        Integer res = jdbcTemplate.queryForObject(
                "SELECT MAX(Song_id) FROM Song",
                Integer.class);
        if (res == null)
            return 0;
        return res;
    }

    public List<Song> index() {
        return jdbcTemplate.query(
                "SELECT * FROM Song",
                new BeanPropertyRowMapper<>(Song.class));
    }

    public int save(Song song) {
        int id = count() + 1;
        jdbcTemplate.update(
                "INSERT INTO Song VALUES (?, ?, ?, ?, 0)",
                id,
                song.getName(),
                song.getMain_artist_id(),
                song.getSong_file());
        return id;
    }

    public Song show(int id) {
        return jdbcTemplate.query(
                "SELECT * FROM Song WHERE Song_id=?",
                        new Object[]{id},
                        new BeanPropertyRowMapper<>(Song.class))
                .stream()
                .findAny()
                .orElse(null);
    }

    public void update(int id, Song updatedSong) {
        jdbcTemplate.update(
                "UPDATE Song SET Name=?, Main_artist_id=?, Song_file=? WHERE Song_id=?",
                updatedSong.getName(),
                updatedSong.getMain_artist_id(),
                updatedSong.getSong_file(),
                id);
    }

    public void delete(int id) {
        jdbcTemplate.update(
                "DELETE FROM Song WHERE Song_id=?",
                id);
    }

    public List<Song> showByArtistId(int id) {
        return jdbcTemplate.query(
                        "SELECT * FROM Song WHERE Main_artist_id=? ORDER BY Name",
                        new Object[]{id},
                        new BeanPropertyRowMapper<>(Song.class));
    }

    public List<Song> showByPartName(String partname1, String partname2) {
        return jdbcTemplate.query(
                "SELECT * FROM Song WHERE Name LIKE ? OR Name LIKE ?",
                new Object[]{partname1, partname2},
                new BeanPropertyRowMapper<>(Song.class));
    }

    public void updateListening(int id) {
        jdbcTemplate.update(
                "UPDATE Song SET Listening=Listening + 1 WHERE Song_id=?",
                id);
    }
}
