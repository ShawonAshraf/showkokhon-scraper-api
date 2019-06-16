package com.showkokhon.scraper.showkokhonscraper.model;

public class PlayingAt {
    private String name;
    private ShowTimes showTimes;

    public PlayingAt(String name) {
        this.name = name;
        showTimes = new ShowTimes();
    }

    public String getName() {
        return name;
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
                "name='" + name + '\'' +
                ", showTimes=" + showTimes +
                '}';
    }
}