package com.showkokhon.scraper.showkokhonscraper.utils;

import com.showkokhon.scraper.showkokhonscraper.model.BasicScraperResponse;
import kong.unirest.Unirest;

import java.util.HashMap;

public class ApiClient {
    private final String urlString = "http://cineplexbd.com/cineplexbd/getShowTime";
    private final String formDataKey = "locationCode";
    private final String contentType = "multipart/form-data";
    private final String formBoundary = "GGWP";

    private final String headerValue = String.format("%s; boundary=%s", contentType, formBoundary);

    private HashMap<String, String> locationCodeMap;

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
    public BasicScraperResponse fetch(String location) {
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
                    new BasicScraperResponse(res.getStatus(), res.getBody()) : new BasicScraperResponse(
                            res.getStatus(), "Couldn't Fetch Data Due WebSite Error");

            return response;
        } catch (Exception e) {
            e.printStackTrace();
            return new BasicScraperResponse(404, "Couldn't Fetch Data Due WebSite Error");
        }
    }

}