package com.showkokhon.scraper.showkokhonscraper.model;

import java.util.ArrayList;

public class PlayingAt {
    private int cinemaId;
    private String locationName;
    private ArrayList<String> showTimes;

    public PlayingAt(int cinemaId, String locationName) {
        this.cinemaId = cinemaId;
        this.locationName = locationName;
        showTimes = new ArrayList<>();
    }

    public ArrayList<String> getShowTimes() {
        return showTimes;
    }

    public void setShowTimes(ArrayList<String> showTimes) {
        this.showTimes = showTimes;
    }

    @Override
    public String toString() {
        return "PlayingAt{" +
                "cinemaId=" + cinemaId +
                ", locationName='" + locationName + '\'' +
                ", showTimes=" + showTimes +
                '}';
    }

    public int getCinemaId() {
        return cinemaId;
    }

    public String getLocationName() {
        return locationName;
    }
}