package com.showkokhon.scraper.showkokhonscraper.model;

import java.util.Date;

public class BasicScraperResponse {
    public int STATUS_CODE;
    public String DATA;
    public String MSG;
    public String SENT_AT;

    public BasicScraperResponse(int STATUS_CODE, String MSG, String DATA) {
        this.STATUS_CODE = STATUS_CODE;
        this.MSG = MSG;
        this.DATA = DATA;

        this.SENT_AT = new Date().toString();
    }
}
