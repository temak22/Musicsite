package ru.mirea.musicsite.entities;


public class Chart {

    private int chart_id;

    private String name;

    private String cover_file;


    public Chart(int chart_id, String name, String cover_file) {
        this.chart_id = chart_id;
        this.name = name;
        this.cover_file = cover_file;
    }

    public Chart() {}

    public int getChart_id() {
        return chart_id;
    }

    public void setChart_id(int chart_id) {
        this.chart_id = chart_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCover_file() {
        return cover_file;
    }

    public void setCover_file(String cover_file) {
        this.cover_file = cover_file;
    }
}
