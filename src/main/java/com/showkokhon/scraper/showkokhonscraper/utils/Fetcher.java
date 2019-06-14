package com.showkokhon.scraper.showkokhonscraper.utils;

import com.showkokhon.scraper.showkokhonscraper.model.Movie;
import com.showkokhon.scraper.showkokhonscraper.scraper.StarCineplexScraper;

import java.util.ArrayList;

public class Fetcher {
    public static ArrayList<Movie> getStarCineplexMoviesByLocation(String location) {
        var client = new ApiClient();
        var res = client.fetch(location);

        if (res.STATUS_CODE != 200) {
            // return an empty arraylist, since there's no data
            // due to error
            return new ArrayList<Movie>();
        } else {
            var scraper = new StarCineplexScraper();
            var parsed = scraper.parse(res.MSG, location);
            return parsed;
        }
    }

    public static ArrayList<Movie> getAllStarCineplexMovies() {
        var bcity = Fetcher.getStarCineplexMoviesByLocation("Bashundhara Shopping Mall, Panthapath");
        var ss = Fetcher.getStarCineplexMoviesByLocation("Shimanto Shambhar, Dhanmondi 2");

        var merged = ListMerger.mergeLists(bcity, ss);
        return merged;
    }

    public static ArrayList<Movie> getAllMovies() {
        var star = getAllStarCineplexMovies();

        // for now
        return star;
    }
}
