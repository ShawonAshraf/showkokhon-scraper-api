package com.showkokhon.scraper.showkokhonscraper.model;

import java.util.ArrayList;

public class Schedule {
    private String date;
    private ArrayList<PlayingAt> playingAt;

    public Schedule(String date, ArrayList<PlayingAt> playingAt) {
        this.date = date;
        this.playingAt = playingAt;
    }

    public String getDate() {
        return date;
    }

    public ArrayList<PlayingAt> getPlayingAt() {
        return playingAt;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "date='" + date + '\'' +
                ", playingAt=" + playingAt +
                '}';
    }
}
