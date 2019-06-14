package com.showkokhon.scraper.showkokhonscraper.model;

import java.util.ArrayList;
import java.util.Date;

public class ScraperResponseWithMovies  {
    public int STATUS_CODE;
    public String SENT_AT;
    public ArrayList<Movie> movies;

    public ScraperResponseWithMovies(int STATUS_CODE, ArrayList<Movie> movies) {
        this.STATUS_CODE = STATUS_CODE;
        this.movies = movies;
        SENT_AT = new Date().toString();
    }
}
