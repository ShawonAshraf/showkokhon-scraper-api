package com.showkokhon.scraper.showkokhonscraper.model;

public class PlayingAt {
    private int cinemaId;
    private String locationName;
    private ShowTimes showTimes;

    public PlayingAt(int cinemaId, String locationName) {
        this.cinemaId = cinemaId;
        this.locationName = locationName;
        showTimes = new ShowTimes();
    }

    public ShowTimes getShowTimes() {
        return showTimes;
    }

    public void setShowTimes(ShowTimes showTimes) {
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