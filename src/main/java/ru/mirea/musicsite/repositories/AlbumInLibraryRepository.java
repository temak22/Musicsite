package ru.mirea.musicsite.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.mirea.musicsite.entities.AlbumInLibrary;

import java.util.List;

@Repository
public class AlbumInLibraryRepository {

    public final JdbcTemplate jdbcTemplate;

    @Autowired
    public AlbumInLibraryRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<AlbumInLibrary> index() {
        return jdbcTemplate.query(
                "SELECT * FROM Album_in_library",
                new BeanPropertyRowMapper<>(AlbumInLibrary.class));
    }

    public int save(AlbumInLibrary albumInLibrary) {
        return jdbcTemplate.update(
                "INSERT INTO Album_in_library VALUES (?, ?)",
                albumInLibrary.getUser_id(),
                albumInLibrary.getAlbum_id());
    }

    public AlbumInLibrary show(int user_id, int album_id) {
        return jdbcTemplate.query(
                        "SELECT * FROM Album_in_library WHERE User_id=? AND Album_id=?",
                        new Object[]{user_id, album_id},
                        new BeanPropertyRowMapper<>(AlbumInLibrary.class))
                .stream()
                .findAny()
                .orElse(null);
    }

    public List<AlbumInLibrary> showByUserId(int id) {
        return jdbcTemplate.query(
                "SELECT * FROM Album_in_library WHERE User_id=?",
                new Object[]{id},
                new BeanPropertyRowMapper<>(AlbumInLibrary.class));
    }

    public void delete(int user_id, int album_id) {
        jdbcTemplate.update(
                "DELETE FROM Album_in_library WHERE Album_id=? AND User_id=?",
                album_id,
                user_id);
    }
}
