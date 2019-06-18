package com.showkokhon.scraper.showkokhonscraper.model;

import java.util.ArrayList;

public class Movie {
    private String name;
    private String mediaType;
    private ArrayList<Schedule> schedule;
    private String imageUrl;

    public Movie(String name, String mediaType) {
        this.name = name;
        this.mediaType = mediaType;
        imageUrl = "";
        schedule = new ArrayList<>();
    }

    public String getMediaType() {
        return mediaType;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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
                ", mediaType='" + mediaType + '\'' +
                ", schedule=" + schedule +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }

    public void setSchedule(ArrayList<Schedule> schedule) {
        this.schedule = schedule;
    }
}
