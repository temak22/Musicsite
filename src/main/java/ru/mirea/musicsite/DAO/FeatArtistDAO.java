package ru.mirea.musicsite.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.mirea.musicsite.entities.FeatArtist;
import ru.mirea.musicsite.entities.Song;
import ru.mirea.musicsite.entities.SongInAlbum;

import java.util.List;

@Repository
public class FeatArtistDAO {

    public final JdbcTemplate jdbcTemplate;

    @Autowired
    public FeatArtistDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<FeatArtist> index() {
        return jdbcTemplate.query(
                "SELECT * FROM Featuring_artist",
                new BeanPropertyRowMapper<>(FeatArtist.class));
    }

    public int save(FeatArtist featArtist) {
        return jdbcTemplate.update(
                "INSERT INTO Featuring_artist VALUES (?, ?)",
                featArtist.getSong_id(),
                featArtist.getFeat_artist_id());
    }

    public List<FeatArtist> showBySongId(int id) {
        return jdbcTemplate.query(
                        "SELECT * FROM Featuring_artist WHERE Song_id=?",
                        new Object[]{id},
                        new BeanPropertyRowMapper<>(FeatArtist.class));
    }

    public List<FeatArtist> showByArtistId(int id) {
        return jdbcTemplate.query(
                "SELECT * FROM Featuring_artist WHERE Feat_artist_id=?",
                new Object[]{id},
                new BeanPropertyRowMapper<>(FeatArtist.class));
    }

    public void delete(int song_id, int artist_id) {
        jdbcTemplate.update(
                "DELETE FROM Featuring_artist WHERE Song_id=? AND Feat_artist_id=?",
                song_id,
                artist_id);
    }
}
