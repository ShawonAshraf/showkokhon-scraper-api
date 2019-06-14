package com.showkokhon.scraper.showkokhonscraper.model;

import java.util.HashMap;

public class Movie {
    private String name;
    /**
     * date -> location -> showTime map
     */
    private HashMap<String, HashMap<String, ShowTimes>> schedule;

    public Movie(String name) {
        this.name = name;
        schedule = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public HashMap<String, HashMap<String, ShowTimes>> getSchedule() {
        return schedule;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "name='" + name + '\'' +
                ", schedule=" + schedule +
                '}';
    }
}