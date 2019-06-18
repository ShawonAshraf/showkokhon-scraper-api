package com.showkokhon.scraper.showkokhonscraper.utils;

import com.showkokhon.scraper.showkokhonscraper.model.BasicScraperResponse;
import kong.unirest.Unirest;

public class BlockbusterCinemasClient {
    // generic error message
    private final String WEBSITE_ERROR = "Couldn't Fetch Data Due To WebSite Error";
    private final String url = "https://blockbusterbd.com/schedule.php";

    public BasicScraperResponse fetch(String date) {
        var requestString = String.format("%s?request=%s", url, date);
        var response = Unirest.get(requestString).asString();

        return response.getStatus() == 200 ?
                new BasicScraperResponse(
                        response.getStatus(),
                        response.getStatusText(),
                        response.getBody()) : new BasicScraperResponse(
                response.getStatus(), WEBSITE_ERROR, null);
    }
}
