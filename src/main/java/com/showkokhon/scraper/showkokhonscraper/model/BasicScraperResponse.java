package com.showkokhon.scraper.showkokhonscraper.model;

import java.util.Date;

public class BasicScraperResponse {
    public int STATUS_CODE;
    public String MSG;
    public String SENT_AT;

    public BasicScraperResponse(int STATUS_CODE, String MSG) {
        this.STATUS_CODE = STATUS_CODE;
        this.MSG = MSG;

        this.SENT_AT = new Date().toString();
    }
}
