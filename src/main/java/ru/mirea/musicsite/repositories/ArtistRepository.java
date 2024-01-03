package ru.mirea.musicsite.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.mirea.musicsite.entities.Artist;

import java.util.List;

@Repository
public class ArtistRepository {

    public final JdbcTemplate jdbcTemplate;

    @Autowired
    public ArtistRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public int count() {
        Integer res = jdbcTemplate.queryForObject(
                "SELECT MAX(Artist_id) FROM Artist",
                Integer.class);
        if (res == null)
            return 0;
        return res;
    }

    public List<Artist> index() {
        return jdbcTemplate.query(
                "SELECT * FROM Artist",
                new BeanPropertyRowMapper<>(Artist.class));
    }

    public int save(Artist artist) {
        int id = artist.getArtist_id();
        if (id == 0)
            id = count() + 1;
        jdbcTemplate.update(
                "INSERT INTO Artist VALUES (?, ?, ?, ?, ?, ?)",
                id,
                artist.getNickname(),
                artist.getEmail(),
                artist.getPhone(),
                artist.getAvatar_file(),
                artist.getPage_photo_file());
        return id;
    }

    public Artist show(int id) {
        return jdbcTemplate.query(
                        "SELECT * FROM Artist WHERE Artist_id=?",
                        new Object[]{id},
                        new BeanPropertyRowMapper<>(Artist.class))
                .stream()
                .findAny()
                .orElse(null);
    }

    public void update(int id, Artist updatedArtist) {
        jdbcTemplate.update(
                "UPDATE Artist SET Nickname=?, Email=?, Phone=?, Avatar_file=?, Page_photo_file=? WHERE Artist_id=?",
                updatedArtist.getNickname(),
                updatedArtist.getEmail(),
                updatedArtist.getPhone(),
                updatedArtist.getAvatar_file(),
                updatedArtist.getPage_photo_file(),
                id);
    }

    public void delete(int id) {
        jdbcTemplate.update(
                "DELETE FROM Artist WHERE Artist_id=?",
                id);
    }

    public List<Artist> showByPartName(String partname1, String partname2) {
        return jdbcTemplate.query(
                "SELECT * FROM Artist WHERE Nickname LIKE ? OR Nickname LIKE ?",
                new Object[]{partname1, partname2},
                new BeanPropertyRowMapper<>(Artist.class));
    }
}