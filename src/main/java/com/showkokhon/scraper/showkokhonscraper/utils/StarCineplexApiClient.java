package com.showkokhon.scraper.showkokhonscraper.utils;

import com.showkokhon.scraper.showkokhonscraper.model.BasicScraperResponse;
import kong.unirest.Unirest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

public class StarCineplexApiClient {
    Logger logger = LoggerFactory.getLogger(StarCineplexApiClient.class);

    // required for star cineplex
    private final String urlString = "http://cineplexbd.com/cineplexbd/getShowTime";
    private final String formDataKey = "locationCode";
    private final String contentType = "multipart/form-data";
    private final String formBoundary = "GGWP";
    // ##

    // generic error message
    private final String WEBSITE_ERROR = "Couldn't Fetch Data Due To WebSite Error";

    // required for star cineplex
    private final String headerValue = String.format("%s; boundary=%s", contentType, formBoundary);

    private HashMap<String, String> locationCodeMap;
    // ##

    public StarCineplexApiClient() {
        locationCodeMap = new HashMap<>();
        populateLocationCodeMap();
    }

    /**
     * Populate locationCodeMap
     */
    private void populateLocationCodeMap() {
        locationCodeMap.put("Bashundhara Shopping Mall, Panthapath", "CP");
        locationCodeMap.put("Shimanto Shambhar, Dhanmondi 2", "SS");
        locationCodeMap.put("SKS Tower, Mohakhali", "SKS");
    }

    /**
     * Fetch MSG from the API
     * returns MSG as a BasicScraperResponse
     */
    public BasicScraperResponse fetch(String location) {
        String body = String.format("--%s\r\nContent-Disposition: form-data; name=\"%s\"\r\n\r\n%s\r\n--%s--",
                formBoundary,
                formDataKey,
                locationCodeMap.get(location),
                formBoundary
        );

        try {
            var res = Unirest.post(urlString)
                    .connectTimeout(10 * 1000)
                    .header("content-type", headerValue)
                    .header("cache-control", "no-cache")
                    .body(body)
                    .asString();


            var response = res.getStatus() == 200 ?
                    new BasicScraperResponse(res.getStatus(), res.getStatusText(), res.getBody()) : new BasicScraperResponse(
                            res.getStatus(), WEBSITE_ERROR, null);

            logger.trace(res.getStatusText());

            return response;
        } catch (Exception e) {
            logger.error("Error getting data from Star Cineplex.");
            return new BasicScraperResponse(404, WEBSITE_ERROR, null);
        }
    }

}