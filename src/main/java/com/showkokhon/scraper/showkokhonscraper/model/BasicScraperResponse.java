package com.showkokhon.scraper.showkokhonscraper.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class BasicScraperResponse {
    public int STATUS_CODE;
    public String DATA;
    public String MSG;
    public String SENT_AT;

    public BasicScraperResponse(int STATUS_CODE, String MSG, String DATA) {
        this.STATUS_CODE = STATUS_CODE;
        this.MSG = MSG;
        this.DATA = DATA;

        this.SENT_AT = getISODate();
    }

    private String getISODate() {
        TimeZone timeZone = TimeZone.getTimeZone("UTC");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        dateFormat.setTimeZone(timeZone);

        return dateFormat.format(new Date());
    }
}
