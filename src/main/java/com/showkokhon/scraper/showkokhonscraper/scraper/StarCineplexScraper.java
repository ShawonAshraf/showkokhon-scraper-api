package com.showkokhon.scraper.showkokhonscraper.scraper;

import com.showkokhon.scraper.showkokhonscraper.model.Movie;
import com.showkokhon.scraper.showkokhonscraper.model.ShowTimes;
import org.jsoup.Jsoup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StarCineplexScraper {
    public ArrayList<Movie> parse(String html, String location) {
        var parsed = parseHTML(html, location);
        var cleaned = mapToArrayList(parsed);

        return cleaned;
    }

    /**
     * Extracts show times, e.g. 11:00 AM using the regex
     */
    private ArrayList<String> getShowTimes(String raw) {
        var list = new ArrayList<String>();

        String regex = "(\\d){2}:(\\d){2} (AM|PM)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(raw);

        while (matcher.find()) {
            list.add(matcher.group());
        }


        return list;
    }


    private HashMap<String, Movie> parseHTML(String html, String location) {
        var map = new HashMap<String, Movie>();
        var dateList = new ArrayList<String>();

        var doc = Jsoup.parse(html);

        /**
         * dates are marked up using date-tx class
         */
        var content = doc.getElementsByClass("date-tx");
        content.forEach(c -> dateList.add(c.text().trim()));


        for (int i = 0; i < dateList.size(); i++) {
            var date = dateList.get(i);

            /*
             * Select all the show time info columns and then iterate
             * over them, since there's no other visible way of relating date
             * to movies
             * */
            var cols = doc.getElementsByClass("col-xs-12");
            var timeSelectBlock = cols.eq(i);

            var movieTitles = timeSelectBlock.select("a.text-info");
            var showTimes = timeSelectBlock.select("ul");

            for (int k = 0; k < movieTitles.size(); k++) {
                var name = movieTitles.get(k).text().trim();
                var timesList = getShowTimes(showTimes.get(k).text());

                var times = new ShowTimes(timesList);

                /**
                 * location -> showTime map
                 */
                var loc = new HashMap<String, ShowTimes>();
                loc.put(location, times);


                /**
                 * if a movie already exists in the map,
                 * update. else create a new one
                 */
                if (!map.containsKey(name)) {
                    var m = new Movie(name);
                    m.getLocationWiseTimes().put(date, loc);
                    map.put(name, m);
                } else {
                    var m = map.get(name);
                    m.getLocationWiseTimes().put(date, loc);
                }
            }
        }

        return map;
    }

    /*
     * converts map to ArrayList for parser output
     * */
    private ArrayList<Movie> mapToArrayList(HashMap<String, Movie> movieHashMap) {
        var list = new ArrayList<Movie>();

        /*
         * values to list
         * */
        movieHashMap.forEach((key, value) -> list.add(value));

        return list;
    }
}
