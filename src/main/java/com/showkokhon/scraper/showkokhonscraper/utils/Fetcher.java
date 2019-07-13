package com.showkokhon.scraper.showkokhonscraper.utils;

import com.showkokhon.scraper.showkokhonscraper.data.Constants;
import com.showkokhon.scraper.showkokhonscraper.model.Movie;
import com.showkokhon.scraper.showkokhonscraper.scraper.BlockbusterCinemasScraper;
import com.showkokhon.scraper.showkokhonscraper.scraper.StarCineplexScraper;

import java.util.ArrayList;

public class Fetcher {
    public static ArrayList<Movie> getStarCineplexMoviesByLocation(String location) {
        var client = new StarCineplexApiClient();
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

    public static ArrayList<Movie> getAllStarCineplexMovies() {
        var bcityMovies = Fetcher.getStarCineplexMoviesByLocation(Constants.bashundharaCity);
        var ssMovies = Fetcher.getStarCineplexMoviesByLocation(Constants.shimantoShambhar);

        var merged = ListMerger.mergeLists(bcityMovies, ssMovies);
        return merged;
    }

    public static ArrayList<Movie> getAllMovies() {
        var star = getAllStarCineplexMovies();
        var bb = getAllFromBlocbusterMovies();

        var merged = ListMerger.mergeLists(star, bb);

        // for now
        return merged;
    }

    public static ArrayList<Movie> getAllFromBlocbusterMovies() {
        var client = new BlockbusterCinemasClient();
        var scraper = new BlockbusterCinemasScraper();

        var list = new ArrayList<Movie>();

        var dates = client.getDates();

        dates.forEach(date -> {
            var response = client.fetch(date);
            if (response.DATA != null) {
                var parsed = scraper.parse(response.DATA, date);
                list.addAll(parsed);
            }
        });

        return list;
    }
}
