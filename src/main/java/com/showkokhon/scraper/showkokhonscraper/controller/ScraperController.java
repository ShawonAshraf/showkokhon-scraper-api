package com.showkokhon.scraper.showkokhonscraper.controller;

import com.showkokhon.scraper.showkokhonscraper.data.Constants;
import com.showkokhon.scraper.showkokhonscraper.model.BasicScraperResponse;
import com.showkokhon.scraper.showkokhonscraper.model.ScraperResponseWithMovies;
import com.showkokhon.scraper.showkokhonscraper.utils.Fetcher;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RestController
public class ScraperController {

    @RequestMapping(value = "/scraper/v1/", method = RequestMethod.GET)
    public BasicScraperResponse root() {
        return new BasicScraperResponse(200, "Green All across the board!", null);
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
                // TODO : implement when blockbuster scraper is ready
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

    @RequestMapping(value = "/scraper/v1/schedule/individual/", method = RequestMethod.GET)
    public ScraperResponseWithMovies getScheduleByLocation(@RequestParam int cinemaId, @RequestParam String location) {
        if (cinemaId == 0) {
            switch (location) {
                case "bcity":
                    var bcityMovies = Fetcher.getStarCineplexMoviesByLocation(Constants.bashundharaCity);
                    return new ScraperResponseWithMovies(200, bcityMovies);
                case "ss":
                    var ssMovies = Fetcher.getStarCineplexMoviesByLocation(Constants.shimantoShambhar);
                    return new ScraperResponseWithMovies(200, ssMovies);
                default:
                    return new ScraperResponseWithMovies(404, null);
            }
        } else {
            // TODO : implement when blockbuster scraper is ready
            return new ScraperResponseWithMovies(200, null);
        }
    }
}
