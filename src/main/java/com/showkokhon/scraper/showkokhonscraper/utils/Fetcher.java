package com.showkokhon.scraper.showkokhonscraper.utils;

import com.showkokhon.scraper.showkokhonscraper.model.Movie;
import com.showkokhon.scraper.showkokhonscraper.scraper.StarCineplexScraper;

import java.util.ArrayList;

public class Fetcher {
    public static ArrayList<Movie> getStarCineplexMovieByLocation(String location) {
        var client = new ApiClient();
        var res = client.fetch(location);

        if (res.STATUS_CODE != 200) {
            // return an empty arraylist, since there's no data
            // due to error
            return new ArrayList<Movie>();
        } else {
            var scraper = new StarCineplexScraper();
            var parsed = scraper.parse(res.DATA, location);
            return parsed;
        }
    }
}