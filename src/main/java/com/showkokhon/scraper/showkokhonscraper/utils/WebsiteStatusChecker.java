package com.showkokhon.scraper.showkokhonscraper.utils;

import com.showkokhon.scraper.showkokhonscraper.data.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// check the website status
public class WebsiteStatusChecker {
    private static Logger logger = LoggerFactory.getLogger(WebsiteStatusChecker.class);

    public static boolean isStarCineplexOk() {
        logger.info("Check Star Cineplex website status");

        var error = "<br /><br />\n" +
                "<p style=\"width:100%;\" class=\"text-info text-center\">Sorry for inconvenience; Please visit later, we are going through\n" +
                "\tmaintenance.</p>\n" +
                "<div class=\"col-xs-12 node\" style=\"margin-bottom: 30px;\">\n" +
                "\t<h2 class=\"text-danger text-center title_page\">Select your desired location.</h2>\n" +
                "</div>\n" +
                "\n" +
                "<script type=\"text/javascript\">\n" +
                "\t$('.buy_ticket').click(function () {\n" +
                "          $('#modal-content_buy').modal({\n" +
                "              show: true\n" +
                "          });\n" +
                "      });\n" +
                "</script>";

        var client = new StarCineplexApiClient();
        var res = client.fetch(Constants.bashundharaCity);

        if (res.DATA == null) {
            logger.warn("Star Cineplex website down or under maintenance.");
            return false;
        }

        return true;
    }
}
