package ru.mirea.musicsite.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.mirea.musicsite.entities.SongInAlbum;
import ru.mirea.musicsite.entities.SongInChart;

import java.util.List;

@Repository
public class SongInChartDAO {

    public final JdbcTemplate jdbcTemplate;

    @Autowired
    public SongInChartDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<SongInChart> index() {
        return jdbcTemplate.query(
                "SELECT * FROM Song_in_chart",
                new BeanPropertyRowMapper<>(SongInChart.class));
    }

    public int save(SongInChart songInChart) {
        return jdbcTemplate.update(
                "INSERT INTO Song_in_chart VALUES (?, ?, ?)",
                songInChart.getChart_id(),
                songInChart.getSong_id(),
                songInChart.getSerial_number());
    }

    public List<SongInChart> showByChartId(int id) {
        return jdbcTemplate.query(
                        "SELECT * FROM Song_in_chart WHERE Chart_id=? ORDER BY Serial_number",
                        new Object[]{id},
                        new BeanPropertyRowMapper<>(SongInChart.class));
    }

    public void update(int album_id, int song_id, SongInChart updatedSongInChart) {
        jdbcTemplate.update(
                "UPDATE Song_in_chart SET Serial_number=? WHERE Song_id=? AND Album_id=?",
                updatedSongInChart.getSerial_number(),
                song_id,
                album_id);
    }

    public void delete(int album_id, int song_id) {
        jdbcTemplate.update(
                "DELETE FROM Song_in_chart WHERE Song_id=? AND Album_id=?",
                song_id,
                album_id);
    }
}
