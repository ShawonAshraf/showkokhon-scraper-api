package com.showkokhon.scraper.showkokhonscraper;

import com.showkokhon.scraper.showkokhonscraper.scraper.BlockbusterCinemasScraper;
import com.showkokhon.scraper.showkokhonscraper.scraper.StarCineplexScraper;
import com.showkokhon.scraper.showkokhonscraper.utils.BlockbusterCinemasClient;
import com.showkokhon.scraper.showkokhonscraper.utils.StarCineplexApiClient;
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

	private static String sampleBlockbusterResponse;
	private static String sampleBlockbusterParsedString;

    @Test
    public void scraperShouldReturnCorrectData() {
        initMockData();

        var scraper = new StarCineplexScraper();
        var parsed = scraper.parse(sampleBcityResponseString, "Bashundhara Shopping Mall, Panthapath");

        assertEquals(parsed.toString(), sampleParsedData);

        var bbScraper = new BlockbusterCinemasScraper();
        var jfp = bbScraper.parse(sampleBlockbusterResponse, "2019-06-18");

        assertEquals(jfp.toString(), sampleBlockbusterParsedString);
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
        var client = new StarCineplexApiClient();

        var bcity = client.fetch("Bashundhara Shopping Mall, Panthapath");
        var ss = client.fetch("Shimanto Shambhar, Dhanmondi 2");

        var bbClient = new BlockbusterCinemasClient();
        var jfp = bbClient.bulkFetch();

        assertTrue(!bcity.MSG.equals(WEBSITE_ERROR));
        assertTrue(!ss.MSG.equals(WEBSITE_ERROR));
        assertTrue(!jfp.MSG.equals(WEBSITE_ERROR));
    }

    @Test
    public void blockbusterClientReturnsReponse() {
        var client = new BlockbusterCinemasClient();
        var res = client.bulkFetch();

        assertEquals(res.STATUS_CODE, 200);
    }

    @Test
    public void getFormattedDateReturnsProperFormat() {
        var scraper = new BlockbusterCinemasScraper();
        var date = scraper.getFormattedDate("2019-06-18");

        assertEquals(date, "Tuesday, June 18, 2019");
    }

    @Test
    public void getMediaTypeFromMovieNamesReturnsMediaType() {
        var scraper = new StarCineplexScraper();

        var resA = scraper.getMediaTypeFromMovieName("Agdoom Bagdoom (3D)");
        var resB = scraper.getMediaTypeFromMovieName("Leler Nani (2D)");

        assertTrue(resA.equals("3D"));
        assertTrue(resB.equals("2D"));
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
        sampleParsedData = "[Movie{name='Godzilla: King of the Monsters', mediaType='3D', schedule=[Schedule{date='Saturday, June 15, 2019', playingAt=[PlayingAt{cinemaId=0, locationName='Bashundhara Shopping Mall, Panthapath', showTimes=[02:00 PM, 04:40 PM, 07:20 PM]}]}], imageUrl=''}]";

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
        sampleMergedParsedData = "[Movie{name='Godzilla: King of the Monsters', mediaType='3D', schedule=[Schedule{date='Saturday, June 15, 2019', playingAt=[PlayingAt{cinemaId=0, locationName='Bashundhara Shopping Mall, Panthapath', showTimes=[02:00 PM, 04:40 PM, 07:20 PM]}]}], imageUrl=''}, Movie{name='Godzilla: King of the Monsters', mediaType='3D', schedule=[Schedule{date='Saturday, June 15, 2019', playingAt=[PlayingAt{cinemaId=0, locationName='Shimanto Shambhar, Dhanmondi 2', showTimes=[02:00 PM, 04:40 PM, 07:20 PM]}]}], imageUrl=''}]";


        // blockbuster

        StringBuilder responseJfp = new StringBuilder();
        responseJfp.append("<div class=\"strip_all_rooms_list wow fadeIn\" data-wow-delay=\"0.1s\" style=\"margin: 10px;\">")
                .append("                <div class=\"row\">")
                .append("                    <div class=\"col-lg-5 col-md-5 col-sm-5\">")
                .append("                        <div style=\"text-align:center;\">")
                .append("                            <h3>")
                .append("                                Aladdin")
                .append("                                <b style=\"color:#0F75BC;\">[ 3D ]</b>")
                .append("                            </h3>")
                .append("                            <img height=\"350px\" src=\"https://image.blockbusterbd.net/00408.png\" alt=\"\">")
                .append("                            <p>")
                .append("                                <br>")
                .append("                                <a href=\"movie_details.php?movieid=00408\" class=\"btn_1\">View Movie Details</a>")
                .append("                            </p>")
                .append("                        </div>")
                .append("                    </div>")
                .append("                    <div class=\"clearfix visible-xs-block\">")
                .append("                    </div>")
                .append("                    <div class=\"col-lg-7 col-md-7 col-sm-7\">")
                .append("                        <h5 style=\"color:#0f75bc;\">")
                .append("                            <img src='./img/custom_icon/general_theatre.png'>						Theatre TRANSITION")
                .append("                        </h5>")
                .append("                        <table class=\"table table-striped\">")
                .append("                            <thead>")
                .append("                            <tr>")
                .append("                                <th>Show Time</th>")
                .append("                                <th>Book Now</th>")
                .append("                            </tr>")
                .append("                            </thead>")
                .append("                            <tbody>")
                .append("                            <tr>")
                .append("                                <td>")
                .append("                                    <a class=\"tooltip-1 btn_1\" data-placement=\"top\" href=\"#\" style=\"background:#000; color:#fff; font-size:15px;\" title=\"On Theatre TRANSITION\">")
                .append("                                        11:30 am									</a>")
                .append("                                </td>")
                .append("                                <td>")
                .append("                                    <a class=\"tooltip-1 btn_1\" data-placement=\"top\" style=\"background:#ed1c24; color:#fff; font-size:12px;padding: 3px 10px;\" href=\"booking.php?movieid=00408&showday=2019-06-18&show=01&theatre=05\">Buy Tickets</a>")
                .append("                                </td>")
                .append("                            </tr>")
                .append("                            <tr>")
                .append("                                <td>")
                .append("                                    <a class=\"tooltip-1  btn_1\" data-placement=\"top\" href=\"#\" style=\"background:#000; color:#fff; font-size:15px;\" title=\"On Theatre TRANSITION\">")
                .append("                                        2:15 pm									</a>")
                .append("                                </td>")
                .append("                                <td>")
                .append("                                    <a class=\"tooltip-1 btn_1\" data-placement=\"top\" style=\"background:#ed1c24; color:#fff; font-size:12px;padding: 3px 10px;\" href=\"booking.php?movieid=00408&showday=2019-06-18&show=02&theatre=05\">Buy Tickets</a>")
                .append("                                </td>")
                .append("                            </tr>")
                .append("                            <tr>")
                .append("                                <td>")
                .append("                                    <a class=\"tooltip-1  btn_1\" data-placement=\"top\" href=\"#\" style=\"background:#000; color:#fff; font-size:15px;\" title=\"On Theatre TRANSITION\">")
                .append("                                        4:50 pm									</a>")
                .append("                                </td>")
                .append("                                <td>")
                .append("                                    <a class=\"tooltip-1 btn_1\" data-placement=\"top\" style=\"background:#ed1c24; color:#fff; font-size:12px;padding: 3px 10px;\" href=\"booking.php?movieid=00408&showday=2019-06-18&show=03&theatre=05\">Buy Tickets</a>")
                .append("                                </td>")
                .append("                            </tr>")
                .append("                            <tr>")
                .append("                                <td>")
                .append("                                    <a class=\"tooltip-1 btn_1\" data-placement=\"top\" href=\"#\" style=\"background:#000; color:#fff; font-size:15px;\" title=\"On Theatre TRANSITION\">")
                .append("                                        7:30 pm									</a>")
                .append("                                </td>")
                .append("                                <td>")
                .append("                                    <a class=\"tooltip-1 btn_1\" data-placement=\"top\" style=\"background:#ed1c24; color:#fff; font-size:12px;padding: 3px 10px;\" href=\"booking.php?movieid=00408&showday=2019-06-18&show=04&theatre=05\">Buy Tickets</a>")
                .append("                                </td>")
                .append("                            </tr>")
                .append("                            </tbody>")
                .append("                        </table>")
                .append("                        <h5 style=\"color:#0f75bc;\">")
                .append("                            <img src='./img/custom_icon/royal_theare.png'>")
                .append("                            <img src='./img/custom_icon/royal_class.png'>						Theatre CLUB ROYALE")
                .append("                        </h5>")
                .append("                        <table class=\"table table-striped\">")
                .append("                            <thead>")
                .append("                            <tr>")
                .append("                                <th>Show Time</th>")
                .append("                                <th>Book Now</th>")
                .append("                            </tr>")
                .append("                            </thead>")
                .append("                            <tbody>")
                .append("                            <tr>")
                .append("                                <td>")
                .append("                                    <a class=\"tooltip-1  btn_1\" data-placement=\"top\" href=\"#\" style=\"background:#000; color:#fff; font-size:15px;\" title=\"On Theatre CLUB ROYALE\">")
                .append("                                        2:50 pm									</a>")
                .append("                                </td>")
                .append("                                <td>")
                .append("                                    <a class=\"tooltip-1 btn_1\" data-placement=\"top\" style=\"background:#ed1c24; color:#fff; font-size:12px;padding: 3px 10px;\" href=\"booking.php?movieid=00408&showday=2019-06-18&show=02&theatre=06\">Buy Tickets</a>")
                .append("                                </td>")
                .append("                            </tr>")
                .append("                            <tr>")
                .append("                                <td>")
                .append("                                    <a class=\"tooltip-1 btn_1\" data-placement=\"top\" href=\"#\" style=\"background:#000; color:#fff; font-size:15px;\" title=\"On Theatre CLUB ROYALE\">")
                .append("                                        8:00 pm									</a>")
                .append("                                </td>")
                .append("                                <td>")
                .append("                                    <a class=\"tooltip-1 btn_1\" data-placement=\"top\" style=\"background:#ed1c24; color:#fff; font-size:12px;padding: 3px 10px;\" href=\"booking.php?movieid=00408&showday=2019-06-18&show=04&theatre=06\">Buy Tickets</a>")
                .append("                                </td>")
                .append("                            </tr>")
                .append("                            </tbody>")
                .append("                        </table>")
                .append("                    </div>")
                .append("                </div>")
                .append("            </div>");


        sampleBlockbusterResponse = responseJfp.toString();
        sampleBlockbusterParsedString = "[Movie{name='Aladdin', mediaType='3D', schedule=[Schedule{date='Tuesday, June 18, 2019', playingAt=[PlayingAt{cinemaId=1, locationName='On Theatre CLUB ROYALE', showTimes=[2:50 pm, 8:00 pm]}, PlayingAt{cinemaId=1, locationName='On Theatre TRANSITION', showTimes=[11:30 am, 2:15 pm, 4:50 pm, 7:30 pm]}]}], imageUrl='https://image.blockbusterbd.net/00408.png'}]";
    }

}
