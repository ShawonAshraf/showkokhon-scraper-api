package com.showkokhon.scraper.showkokhonscraper.controller;

import com.showkokhon.scraper.showkokhonscraper.model.BasicScraperResponse;
import com.showkokhon.scraper.showkokhonscraper.model.ScraperResponseWithMovies;
import com.showkokhon.scraper.showkokhonscraper.utils.Fetcher;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RestController
public class ScraperController {

    @RequestMapping(value = "/scraper/v1/", method = RequestMethod.GET)
    public BasicScraperResponse root() {
        return new BasicScraperResponse(200, "Green All across the board!");
    }

    @RequestMapping(value = "/scraper/v1/schedule/", method = RequestMethod.GET)
    public ScraperResponseWithMovies getScheduleByCinemaId(@RequestParam int cinemaId) {
        ScraperResponseWithMovies response;

        switch (cinemaId) {
            case 0:
                // star cineplex
                var movies = Fetcher.getAllStarCineplexMovies();
                response = new ScraperResponseWithMovies(200, movies);

                return response;

            case 1:
                // blockbuster
                response = new ScraperResponseWithMovies(200, null);
                return response;
            default:
                response = new ScraperResponseWithMovies(404, null);
                return response;
        }
    }

    @RequestMapping(value = "/scraper/v1/schedule/all", method = RequestMethod.GET)
    public ScraperResponseWithMovies getAllSchedule() {
        var movies = Fetcher.getAllMovies();
        return new ScraperResponseWithMovies(200, movies);
    }
}
