package ru.mirea.musicsite.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.mirea.musicsite.entities.Album;

import java.util.List;

@Repository
public class AlbumDAO {

    public final JdbcTemplate jdbcTemplate;

    @Autowired
    public AlbumDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public int count() {
        Integer res = jdbcTemplate.queryForObject(
                "SELECT MAX(Album_id) FROM Album",
                Integer.class);
        if (res == null)
            return 0;
        return res;
    }

    public List<Album> index() {
        return jdbcTemplate.query(
                "SELECT * FROM Album",
                new BeanPropertyRowMapper<>(Album.class));
    }

    public int save(Album album) {
        int id = count() + 1;
        jdbcTemplate.update(
                "INSERT INTO Album VALUES (?, ?, ?, ?, ?, ?)",
                id,
                album.getName(),
                album.getRelease_date(),
                album.getStyle(),
                album.getArtist_id(),
                album.getCover_file());
        return id;
    }

    public Album show(int id) {
        return jdbcTemplate.query(
                "SELECT * FROM Album WHERE Album_id=?",
                        new Object[]{id},
                        new BeanPropertyRowMapper<>(Album.class))
                .stream()
                .findAny()
                .orElse(null);
    }

    public void update(int id, Album updatedAlbum) {
        jdbcTemplate.update(
                "UPDATE Album SET Name=?, Release_date=?, Style=?, Main_artist_id=?, Cover_file=? WHERE Album_id=?",
                updatedAlbum.getName(),
                updatedAlbum.getRelease_date(),
                updatedAlbum.getStyle(),
                updatedAlbum.getArtist_id(),
                updatedAlbum.getCover_file(),
                id);
    }

    public void delete(int id) {
        jdbcTemplate.update(
                "DELETE FROM Album WHERE Album_id=?",
                id);
    }

    public List<Album> showByArtistId(int id) {
        return jdbcTemplate.query(
                        "SELECT * FROM Album WHERE Artist_id=? ORDER BY Release_date DESC",
                        new Object[]{id},
                        new BeanPropertyRowMapper<>(Album.class));
    }

    public List<Album> showByName(String name) {
        return jdbcTemplate.query(
                "SELECT * FROM Album WHERE Name LIKE ?",
                new Object[]{name},
                new BeanPropertyRowMapper<>(Album.class));
    }

    public List<Album> showByPartName(String partname1, String partname2) {
        return jdbcTemplate.query(
                "SELECT * FROM Album WHERE Name LIKE ? OR Name LIKE ?",
                new Object[]{partname1, partname2},
                new BeanPropertyRowMapper<>(Album.class));
    }

}