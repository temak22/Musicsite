package ru.mirea.musicsite.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.mirea.musicsite.entities.SongInAlbum;

import java.util.List;

@Repository
public class SongInAlbumRepository {

    public final JdbcTemplate jdbcTemplate;

    @Autowired
    public SongInAlbumRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<SongInAlbum> index() {
        return jdbcTemplate.query(
                "SELECT * FROM Song_in_album",
                new BeanPropertyRowMapper<>(SongInAlbum.class));
    }

    public int save(SongInAlbum songInAlbum) {
        return jdbcTemplate.update(
                "INSERT INTO Song_in_album VALUES (?, ?, ?, ?, ?)",
                songInAlbum.getAlbum_id(),
                songInAlbum.getSong_id(),
                songInAlbum.getIs_lead_song(),
                songInAlbum.getSerial_number(),
                songInAlbum.getOrder_in_album_number());
    }

    public SongInAlbum showBySongId(int id) {
        return jdbcTemplate.query(
                        "SELECT * FROM Song_in_album WHERE Song_id=?",
                        new Object[]{id},
                        new BeanPropertyRowMapper<>(SongInAlbum.class))
                .stream()
                .findAny()
                .orElse(null);
    }

    public List<SongInAlbum> showByAlbumId(int id) {
        return jdbcTemplate.query(
                        "SELECT * FROM Song_in_album WHERE Album_id=? ORDER BY Order_in_album_number",
                        new Object[]{id},
                        new BeanPropertyRowMapper<>(SongInAlbum.class));
    }

    public void update(int album_id, int song_id, SongInAlbum updatedSongInAlbum) {
        jdbcTemplate.update(
                "UPDATE Song_in_album SET Is_lead_song=?, Serial_number=?, Order_in_album_number=? WHERE Song_id=? AND Album_id=?",
                updatedSongInAlbum.getIs_lead_song(),
                updatedSongInAlbum.getSerial_number(),
                updatedSongInAlbum.getOrder_in_album_number(),
                song_id,
                album_id);
    }

    public void delete(int album_id, int song_id) {
        jdbcTemplate.update(
                "DELETE FROM Song_in_album WHERE Song_id=? AND Album_id=?",
                song_id,
                album_id);
    }
}
