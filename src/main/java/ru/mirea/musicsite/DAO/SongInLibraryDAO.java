package ru.mirea.musicsite.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.mirea.musicsite.entities.FeatArtist;
import ru.mirea.musicsite.entities.SongInLibrary;

import java.util.List;

@Repository
public class SongInLibraryDAO {

    public final JdbcTemplate jdbcTemplate;

    @Autowired
    public SongInLibraryDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<SongInLibrary> index() {
        return jdbcTemplate.query(
                "SELECT * FROM Song_in_library",
                new BeanPropertyRowMapper<>(SongInLibrary.class));
    }

    public int save(SongInLibrary songInLibrary) {
        return jdbcTemplate.update(
                "INSERT INTO Song_in_library VALUES (?, ?)",
                songInLibrary.getUser_id(),
                songInLibrary.getSong_id());
    }

    public int countBySongId(int id) {
        return jdbcTemplate.query(
                        "SELECT COUNT(*) FROM Song_in_library WHERE Song_id=?",
                        new Object[]{id},
                        new BeanPropertyRowMapper<>(Integer.class))
                .stream()
                .findAny()
                .orElse(0);
    }

    public List<SongInLibrary> showByUserId(int id) {
        return jdbcTemplate.query(
                "SELECT * FROM Song_in_library WHERE User_id=?",
                new Object[]{id},
                new BeanPropertyRowMapper<>(SongInLibrary.class));
    }

    public void delete(int user_id, int song_id) {
        jdbcTemplate.update(
                "DELETE FROM Song_in_library WHERE Song_id=? AND User_id=?",
                song_id,
                user_id);
    }
}
