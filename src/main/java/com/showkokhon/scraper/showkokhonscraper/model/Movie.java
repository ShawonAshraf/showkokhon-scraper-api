package com.showkokhon.scraper.showkokhonscraper.model;

import java.util.HashMap;

public class Movie {
    private String name;
    /**
     * date -> location -> showTime map
     */
    private HashMap<String, HashMap<String, ShowTimes>> locationWiseTimes;

    public Movie(String name) {
        this.name = name;
        locationWiseTimes = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public HashMap<String, HashMap<String, ShowTimes>> getLocationWiseTimes() {
        return locationWiseTimes;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "name='" + name + '\'' +
                ", locationWiseTimes=" + locationWiseTimes +
                '}';
    }
}