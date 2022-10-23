package ru.mirea.musicsite.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Chart {
    private int chart_id;
    private String name;
    private String cover_file;
}
