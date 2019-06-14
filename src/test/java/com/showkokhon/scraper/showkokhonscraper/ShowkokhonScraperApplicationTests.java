package com.showkokhon.scraper.showkokhonscraper;

import com.showkokhon.scraper.showkokhonscraper.scraper.StarCineplexScraper;
import com.showkokhon.scraper.showkokhonscraper.utils.ApiClient;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShowkokhonScraperApplicationTests {

	/**
	 * Converted HTML to Java String using
	 * http://pojo.sodhanalibrary.com/string.html
	 */
	private static String sampleResponseString;
	private static String sampleParsedData;

    /**
     * Init mock data
     */
    @BeforeAll
    public static void initMockData() {
        var stringBuilder = new StringBuilder();
        stringBuilder.append("<h2 class=\"title_page\">")
                .append("    <span class=\"date-tx\">")
                .append("        <i class=\"fa fa-calendar\"></i>")
                .append("        <strong>Thursday, April 18, 2019</strong>")
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
                .append("                    <a class=\"text-info\" href=\"http://cineplexbd.com/cineplexbd/movieshowtimedetails/1092/CP\">")
                .append("                      Shazam! (3D)                      </a>")
                .append("                </p>")
                .append("            </div>")
                .append("            <ul class=\"col-sm-8 items-wrap\">")
                .append("                <li class=\"time-select__item\">")
                .append("                              10:50 AM                            </li>")
                .append("                <li class=\"time-select__item\">")
                .append("                              01:40 PM                            </li>")
                .append("                <li class=\"time-select__item\">")
                .append("                              01:50 PM                            </li>")
                .append("                <li class=\"time-select__item\">")
                .append("                              04:35 PM                            </li>")
                .append("                <li class=\"time-select__item\">")
                .append("                              07:10 PM                            </li>")
                .append("                <li class=\"time-select__item\">")
                .append("                              07:30 PM                            </li>")
                .append("            </ul>")
                .append("        </div>")
                .append("    </div>");

        sampleResponseString = stringBuilder.toString();
        sampleParsedData = "[Movie{name='Shazam! (3D)', locationWiseTimes={Thursday, " +
                "April 18, 2019={Bashundhara Shopping Mall, " +
                "Panthapath=ShowTimes{showTimes=[10:50 AM, 01:40 PM, 01:50 PM, " +
                "04:35 PM, 07:10 PM, 07:30 PM]}}}}]";
    }

//	@Test
//	public void contextLoads() {
//	}

    /**
     * Rigorous Test :-)
     */
    @Test
    public void clientReturnsResponseForBCity() {
        var client = new ApiClient();

        var response = client.fetch("Bashundhara Shopping Mall, Panthapath");
        var status = response.STATUS_CODE;
        var message = response.MSG;

        assertEquals(200, status);
        assertEquals("OK", message);
    }

    @Test
    public void clientReturnsResponseForShimantoShambhar() {
        var client = new ApiClient();

        var response = client.fetch("Shimanto Shambhar, Dhanmondi 2");
        var status = response.STATUS_CODE;
        var message = response.MSG;

        assertEquals(200, status);
        assertEquals("OK", message);
    }
}
