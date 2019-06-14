package com.showkokhon.scraper.showkokhonscraper.controller;

import com.showkokhon.scraper.showkokhonscraper.model.BasicScraperResponse;
import com.showkokhon.scraper.showkokhonscraper.model.Movie;
import com.showkokhon.scraper.showkokhonscraper.model.ScraperResponseWithMovies;
import com.showkokhon.scraper.showkokhonscraper.utils.Fetcher;
import com.showkokhon.scraper.showkokhonscraper.utils.ListMerger;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@CrossOrigin
@RestController
public class ScraperController {

    @RequestMapping(value = "/scraper/v1/", method = RequestMethod.GET)
    public BasicScraperResponse root() {
        return new BasicScraperResponse(200, "Green All across the board!");
    }

    @RequestMapping(value = "/scraper/v1/schedule/", method = RequestMethod.GET)
    public BasicScraperResponse getAllSchedule(@RequestParam int cinemaId) {

        ScraperResponseWithMovies response = null;

        switch (cinemaId) {
            case 0:
                // star cineplex
                var bcity = Fetcher.getStarCineplexMovieByLocation("Bashundhara Shopping Mall, Panthapath");
                var ss = Fetcher.getStarCineplexMovieByLocation("Shimanto Shambhar, Dhanmondi 2");

                var merged = ListMerger.mergeLists(bcity, ss);

                response = new ScraperResponseWithMovies(200, "OK", merged);
                return response;

            case 1:
                // blockbuster
                response = new ScraperResponseWithMovies(200, "Feature not ready yet",
                        new ArrayList<Movie>());
                return response;
            default:
                break;

        }



        return response;
    }
}
