package com.showkokhon.scraper.showkokhonscraper.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

public class ScraperResponseWithMovies  {
    public int STATUS_CODE;
    public String SENT_AT;
    public ArrayList<Movie> movies;

    public ScraperResponseWithMovies(int STATUS_CODE, ArrayList<Movie> movies) {
        this.STATUS_CODE = STATUS_CODE;
        this.movies = movies;
        SENT_AT = getISODate();
    }

    private String getISODate() {
        TimeZone timeZone = TimeZone.getTimeZone("UTC");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        dateFormat.setTimeZone(timeZone);

        return dateFormat.format(new Date());
    }
}
