package com.showkokhon.scraper.showkokhonscraper.utils;

import com.showkokhon.scraper.showkokhonscraper.model.BasicScraperResponse;
import kong.unirest.Unirest;

import java.util.HashMap;

public class ApiClient {
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

    public ApiClient() {
        locationCodeMap = new HashMap<>();
        populateLocationCodeMap();
    }

    /**
     * Populate locationCodeMap
     */
    private void populateLocationCodeMap() {
        locationCodeMap.put("Bashundhara Shopping Mall, Panthapath", "CP");
        locationCodeMap.put("Shimanto Shambhar, Dhanmondi 2", "SS");
    }

    /**
     * Fetch MSG from the API
     * returns MSG as a BasicScraperResponse
     */
    public BasicScraperResponse fetchStarCineplex(String location) {
        String body = String.format("--%s\r\nContent-Disposition: form-data; name=\"%s\"\r\n\r\n%s\r\n--%s--",
                formBoundary,
                formDataKey,
                locationCodeMap.get(location),
                formBoundary
        );

        try {
            var res = Unirest.post(urlString)
                    .header("content-type", headerValue)
                    .header("cache-control", "no-cache")
                    .body(body)
                    .asString();


            var response = res.getStatus() == 200 ?
                    new BasicScraperResponse(res.getStatus(), res.getStatusText(), res.getBody()) : new BasicScraperResponse(
                            res.getStatus(), WEBSITE_ERROR, null);

            return response;
        } catch (Exception e) {
            e.printStackTrace();
            return new BasicScraperResponse(404, WEBSITE_ERROR, null);
        }
    }

}