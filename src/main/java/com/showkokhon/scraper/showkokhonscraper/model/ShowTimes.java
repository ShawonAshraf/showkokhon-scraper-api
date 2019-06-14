package com.showkokhon.scraper.showkokhonscraper.model;

import java.util.ArrayList;

public class ShowTimes {
    private ArrayList<String> showTimes;

    public ShowTimes() {
        showTimes = new ArrayList<>();
    }

    public ShowTimes(ArrayList<String> showTimes) {
        this.showTimes = showTimes;
    }

    public ArrayList<String> getShowTimes() {
        return showTimes;
    }

    public void setShowTimes(ArrayList<String> showTimes) {
        this.showTimes = showTimes;
    }

    @Override
    public String toString() {
        return "ShowTimes{" +
                "showTimes=" + showTimes +
                '}';
    }
}