package com.showkokhon.scraper.showkokhonscraper.utils;

import com.showkokhon.scraper.showkokhonscraper.model.BasicScraperResponse;
import kong.unirest.Unirest;
import org.jsoup.Jsoup;

import java.time.LocalDate;
import java.util.ArrayList;

public class BlockbusterCinemasClient {
    // generic error message
    private final String WEBSITE_ERROR = "Couldn't Fetch Data Due To WebSite Error";
    private final String url = "https://blockbusterbd.com/schedule.php";

    private final ArrayList<String> dates;

    public BlockbusterCinemasClient() {
        dates = new ArrayList<>();
        prepareDateList();
    }

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

    public BasicScraperResponse bulkFetch() {
        prepareDateList();
        var data = new StringBuilder();

        try {
            dates.forEach(date -> {
                // make request
                var response = fetch(date);
                data.append(response.DATA);
            });

            return new BasicScraperResponse(200, "OK", data.toString());
        } catch (Exception e) {
            return new BasicScraperResponse(
                    500, WEBSITE_ERROR, null);
        }
    }

    // bulkFetch the next 7 days dates
    private void prepareDateList() {
        var today = LocalDate.now();
        dates.add(today.toString());

        for (long i = 1; i < 7; i++) {
            var date = today.plusDays(i);
            dates.add(date.toString());
        }
    }

    public ArrayList<String> getDates() {
        return dates;
    }
}
