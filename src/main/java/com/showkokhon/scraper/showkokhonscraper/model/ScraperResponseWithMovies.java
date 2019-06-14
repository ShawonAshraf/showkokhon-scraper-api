package com.showkokhon.scraper.showkokhonscraper.model;

import java.util.ArrayList;

public class ScraperResponseWithMovies extends BasicScraperResponse {
    public ArrayList<Movie> movies;

    public ScraperResponseWithMovies(int STATUS_CODE, String DATA, ArrayList<Movie> movies) {
        super(STATUS_CODE, DATA);
        this.movies = movies;
    }
}
