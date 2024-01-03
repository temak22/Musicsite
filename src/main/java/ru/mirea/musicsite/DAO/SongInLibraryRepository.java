package ru.mirea.musicsite.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.mirea.musicsite.entities.SongInLibrary;

import java.util.List;

@Repository
public class SongInLibraryRepository {

    public final JdbcTemplate jdbcTemplate;

    @Autowired
    public SongInLibraryRepository(JdbcTemplate jdbcTemplate) {
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

    public int countBySongIdInAllLibraries(int id) {
        return jdbcTemplate.query(
                        "SELECT COUNT(*) FROM Song_in_library WHERE Song_id=?",
                        new Object[]{id},
                        new BeanPropertyRowMapper<>(Integer.class))
                .stream()
                .findAny()
                .orElse(0);
    }

    public SongInLibrary show(int user_id, int song_id) {
        return jdbcTemplate.query(
                        "SELECT * FROM Song_in_library WHERE User_id=? AND Song_id=?",
                        new Object[]{user_id, song_id},
                        new BeanPropertyRowMapper<>(SongInLibrary.class))
                .stream()
                .findAny()
                .orElse(null);
    }

    public List<SongInLibrary> showByUserId(int id) {
        return jdbcTemplate.query(
                "SELECT * FROM Song_in_library_with_name WHERE User_id=? ORDER BY Name",
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
