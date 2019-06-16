package com.showkokhon.scraper.showkokhonscraper.model;

import java.util.ArrayList;
import java.util.HashMap;

public class Movie {
    private String name;
    private ArrayList<Schedule> schedule;

    public Movie(String name) {
        this.name = name;
        schedule = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<Schedule> getSchedule() {
        return schedule;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "name='" + name + '\'' +
                ", schedule=" + schedule +
                '}';
    }

    public void setSchedule(ArrayList<Schedule> schedule) {
        this.schedule = schedule;
    }
}
