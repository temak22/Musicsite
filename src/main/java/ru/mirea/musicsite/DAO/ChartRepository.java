package ru.mirea.musicsite.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.mirea.musicsite.entities.Chart;

import java.util.List;

@Repository
public class ChartRepository {

    public final JdbcTemplate jdbcTemplate;

    @Autowired
    public ChartRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public int count() {
        Integer res = jdbcTemplate.queryForObject(
                "SELECT MAX(Chart_id) FROM Chart",
                Integer.class);
        if (res == null)
            return 0;
        return res;
    }

    public List<Chart> index() {
        return jdbcTemplate.query(
                "SELECT * FROM Chart",
                new BeanPropertyRowMapper<>(Chart.class));
    }

    public int save(Chart chart) {
        int id = count() + 1;
        jdbcTemplate.update(
                "INSERT INTO Chart VALUES (?, ?, ?)",
                id,
                chart.getName(),
                chart.getCover_file());
        return id;
    }

    public Chart show(int id) {
        return jdbcTemplate.query(
                "SELECT * FROM Chart WHERE Chart_id=?",
                        new Object[]{id},
                        new BeanPropertyRowMapper<>(Chart.class))
                .stream()
                .findAny()
                .orElse(null);
    }

    public void update(int id, Chart updatedChart) {
        jdbcTemplate.update(
                "UPDATE Chart SET Name=?, Cover_file=? WHERE Chart_id=?",
                updatedChart.getName(),
                updatedChart.getCover_file(),
                id);
    }

    public void delete(int id) {
        jdbcTemplate.update(
                "DELETE FROM Chart WHERE Chart_id=?",
                id);
    }


    public List<Chart> showByName(String name) {
        return jdbcTemplate.query(
                "SELECT * FROM Chart WHERE Name LIKE ?",
                new Object[]{name},
                new BeanPropertyRowMapper<>(Chart.class));
    }

    public List<Chart> showByPartName(String partname1, String partname2) {
        return jdbcTemplate.query(
                "SELECT * FROM Chart WHERE Name LIKE ? OR Name LIKE ?",
                new Object[]{partname1, partname2},
                new BeanPropertyRowMapper<>(Chart.class));
    }

}