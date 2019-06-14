package com.showkokhon.scraper.showkokhonscraper.controller;

import com.showkokhon.scraper.showkokhonscraper.model.BasicScraperResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ScraperController {

    @RequestMapping(value = "/scraper/v1/", method = RequestMethod.GET)
    public BasicScraperResponse root() {
        return new BasicScraperResponse(200, "Green All across the board!");
    }
}
