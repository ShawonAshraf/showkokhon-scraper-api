package com.showkokhon.scraper.showkokhonscraper;

import com.showkokhon.scraper.showkokhonscraper.scraper.StarCineplexScraper;
import com.showkokhon.scraper.showkokhonscraper.utils.ApiClient;
import com.showkokhon.scraper.showkokhonscraper.utils.ListMerger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShowkokhonScraperApplicationTests {

	/**
	 * Converted HTML to Java String using
	 * http://pojo.sodhanalibrary.com/string.html
	 */
	private static String sampleBcityResponseString;
	private static String sampleSSResponseString;

	private static String sampleParsedData;
	private static String sampleMergedParsedData;

    @Test
    public void scraperShouldReturnCorrectData() {
        initMockData();

        var scraper = new StarCineplexScraper();
        var parsed = scraper.parse(sampleBcityResponseString, "Bashundhara Shopping Mall, Panthapath");

        assertEquals(parsed.toString(), sampleParsedData);
    }

    @Test
    public void listMergerShouldMergeTwoLists() {
        initMockData();

        var scraper = new StarCineplexScraper();
        var bcity = scraper.parse(sampleBcityResponseString, "Bashundhara Shopping Mall, Panthapath");
        var ss = scraper.parse(sampleSSResponseString, "Shimanto Shambhar, Dhanmondi 2");

        var merged = ListMerger.simpleMerge(bcity, ss);

        assertEquals(merged.toString(), sampleMergedParsedData);
    }

    @Test
    public void apiClientReturnsSomething() {
        final String WEBSITE_ERROR = "Couldn't Fetch Data Due To WebSite Error";
        var client = new ApiClient();

        var bcity = client.fetchStarCineplex("Bashundhara Shopping Mall, Panthapath");
        var ss = client.fetchStarCineplex("Shimanto Shambhar, Dhanmondi 2");

        assertTrue(!bcity.MSG.equals(WEBSITE_ERROR));
        assertTrue(!ss.MSG.equals(WEBSITE_ERROR));
    }


    /**
     * Init mock data
     */
    public static void initMockData() {
        var response1 = new StringBuilder();
        response1.append("<div class=\"node\" style=\"margin-top: 40px;\">")
                .append("    <h3 class=\"text-center title_page\" id=\"movie_title\" style=\"border-bottom: 1px dotted black;padding-top: 50px;margin-bottom: 20px; color:#5f1a89\">Movie showtime for Bashundhara Shopping Mall, Panthapath</h3>")
                .append("</div>")
                .append("<h2 class=\"title_page\">")
                .append("    <span class=\"date-tx\">")
                .append("        <i class=\"fa fa-calendar\"></i>")
                .append("        <strong>Saturday, June 15, 2019</strong>")
                .append("    </span>")
                .append("    <span class=\"buy-ticket-tx\">")
                .append("        <a href=\"javascript:void(0)\" class=\"btn btn-xs btn-primary buy_ticket\" >Buy Ticket</a>")
                .append("    </span>")
                .append("</h2>")
                .append("<br />")
                .append("<div class=\"col-xs-12\" style=\"margin-bottom: 30px;\">")
                .append("    <div class=\"time-select\" style=\"display: block;\">")
                .append("        <div class=\"time-select__group group--first\">")
                .append("            <div class=\"col-sm-4\">")
                .append("                <p class=\"time-select__place\">")
                .append("                    <a class=\"text-info\" href=\"http://cineplexbd.com/cineplexbd/movieshowtimedetails/1098/CP\">")
                .append("                        Godzilla: King of the Monsters (3D)                      </a>")
                .append("                </p>")
                .append("            </div>")
                .append("            <ul class=\"col-sm-8 items-wrap\">")
                .append("                <li class=\"time-select__item_6\">")
                .append("                    02:00 PM                            </li>")
                .append("                <li class=\"time-select__item_6\">")
                .append("                    04:40 PM                            </li>")
                .append("                <li class=\"time-select__item_6\">")
                .append("                    07:20 PM                            </li>")
                .append("            </ul>")
                .append("        </div>")
                .append("    </div>")
                .append("</div>");



        sampleBcityResponseString = response1.toString();
        sampleParsedData = "[Movie{name='Godzilla: King of the Monsters (3D)', schedule=[Schedule{date='Saturday, June 15, 2019', playingAt=[PlayingAt{cinemaId=0, locationName='Bashundhara Shopping Mall, Panthapath', showTimes=[02:00 PM, 04:40 PM, 07:20 PM]}]}]}]";

        var response2 = new StringBuilder();
        response2.append("<div class=\"node\" style=\"margin-top: 40px;\">")
                .append("    <h3 class=\"text-center title_page\" id=\"movie_title\" style=\"border-bottom: 1px dotted black;padding-top: 50px;margin-bottom: 20px; color:#5f1a89\">Shimanto Shambhar, Dhanmondi 2</h3>")
                .append("</div>")
                .append("<h2 class=\"title_page\">")
                .append("    <span class=\"date-tx\">")
                .append("        <i class=\"fa fa-calendar\"></i>")
                .append("        <strong>Saturday, June 15, 2019</strong>")
                .append("    </span>")
                .append("    <span class=\"buy-ticket-tx\">")
                .append("        <a href=\"javascript:void(0)\" class=\"btn btn-xs btn-primary buy_ticket\" >Buy Ticket</a>")
                .append("    </span>")
                .append("</h2>")
                .append("<br />")
                .append("<div class=\"col-xs-12\" style=\"margin-bottom: 30px;\">")
                .append("    <div class=\"time-select\" style=\"display: block;\">")
                .append("        <div class=\"time-select__group group--first\">")
                .append("            <div class=\"col-sm-4\">")
                .append("                <p class=\"time-select__place\">")
                .append("                    <a class=\"text-info\" href=\"http://cineplexbd.com/cineplexbd/movieshowtimedetails/1098/CP\">")
                .append("                        Godzilla: King of the Monsters (3D)                      </a>")
                .append("                </p>")
                .append("            </div>")
                .append("            <ul class=\"col-sm-8 items-wrap\">")
                .append("                <li class=\"time-select__item_6\">")
                .append("                    02:00 PM                            </li>")
                .append("                <li class=\"time-select__item_6\">")
                .append("                    04:40 PM                            </li>")
                .append("                <li class=\"time-select__item_6\">")
                .append("                    07:20 PM                            </li>")
                .append("            </ul>")
                .append("        </div>")
                .append("    </div>")
                .append("</div>");


        sampleSSResponseString = response2.toString();
        sampleMergedParsedData = "[Movie{name='Godzilla: King of the Monsters (3D)', schedule=[Schedule{date='Saturday, June 15, 2019', playingAt=[PlayingAt{cinemaId=0, locationName='Bashundhara Shopping Mall, Panthapath', showTimes=[02:00 PM, 04:40 PM, 07:20 PM]}]}]}, Movie{name='Godzilla: King of the Monsters (3D)', schedule=[Schedule{date='Saturday, June 15, 2019', playingAt=[PlayingAt{cinemaId=0, locationName='Shimanto Shambhar, Dhanmondi 2', showTimes=[02:00 PM, 04:40 PM, 07:20 PM]}]}]}]";
    }

}
