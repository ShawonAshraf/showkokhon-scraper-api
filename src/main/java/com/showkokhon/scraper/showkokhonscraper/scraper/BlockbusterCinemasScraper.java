package com.showkokhon.scraper.showkokhonscraper.scraper;

import com.showkokhon.scraper.showkokhonscraper.model.Movie;
import com.showkokhon.scraper.showkokhonscraper.model.PlayingAt;
import com.showkokhon.scraper.showkokhonscraper.model.Schedule;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BlockbusterCinemasScraper {
    public ArrayList<Movie> parse(String html, String date) {
        var parsed = parseHTML(html, date);
        var cleaned = mapToArrayList(parsed);

        return cleaned;
    }



    public String getFormattedDate(String d) {
        var formatter = new SimpleDateFormat("yyyy-MM-dd");

        try {
            var date = formatter.parse(d);

            var newformat = new SimpleDateFormat("EEEE, MMMM d, Y");
            var formatted = newformat.format(date);

            return formatted;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public HashMap<String, Movie> parseHTML(String raw, String date) {
        var map = new HashMap<String, Movie>();
        var html = Jsoup.parse(raw);
        final var cinemaId = 1;

        var divs = html.getElementsByClass("strip_all_rooms_list wow fadeIn");

        for (int i = 0; i < divs.size(); i++) {
            var current = divs.eq(i);

            var tableNode = current.select("table");
            var timeNodes = tableNode.select("td");

            // not to be confused with the showTimesArray
            var showTimes = getShowTimesFromTimeNode(timeNodes);

            // create a playingAtList
            var playingAtList = new ArrayList<PlayingAt>();
            showTimes.forEach((k, v) -> {
                var playingAt = new PlayingAt(cinemaId, k);
                playingAt.setShowTimes(v);

                playingAtList.add(playingAt);
            });

            // create a schedule
            var formattedDate = getFormattedDate(date);
            var schedule = new Schedule(formattedDate, playingAtList);


            // get movie name
            var nameNode = current.select("h3");
            var name = getMovieNameFromNode(nameNode.text());

            // now check if the movie exists in the map or not
            if (!map.containsKey(name)) {
                // create a movie instance
                var mediaType = getMediaTypeFromNode(nameNode.text());

                var movie = new Movie(name, mediaType);
                var imageUrl = getImageUrlFromNode(current);
                movie.setImageUrl(imageUrl);

                // add schedule
                movie.getSchedule().add(schedule);

                map.put(name, movie);
            } else {
                map.get(name).getSchedule().add(schedule);
            }
        }

        return map;
    }

    public String getImageUrlFromNode(Elements node) {
        var img = node.select("img");
        return img.attr("src");
    }

    // theatre -> showTime
    // location -> showTime
    public HashMap<String, ArrayList<String>> getShowTimesFromTimeNode(Elements timeNode) {
        var map = new HashMap<String, ArrayList<String>>();

        timeNode.forEach(node -> {
            var time = node.text();

            if (!time.equals("Buy Tickets")) {
                var location = node.select("a").attr("title");
                if (!map.containsKey(location)) {
                    map.put(location, new ArrayList<>());
                }
                map.get(location).add(time);
            }
        });

        return map;
    }

    public String getMediaTypeFromNode(String nodeText) {
        final var regex = "(\\[ (\\d)D \\])";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(nodeText);

        String mediaType = "";

        while (matcher.find()) {
            var grp = matcher.group();
            if (grp != null) {
                mediaType = grp;
                break;
            }
        }

        mediaType = mediaType.replaceAll("\\[ ", "");
        mediaType = mediaType.replaceAll(" \\]", "");

        return mediaType;
    }

    public String getFormattedNameNode(String nameNodeString) {
        final var regexPattern = "(\\[ (\\d)D \\])";
        var formatted = nameNodeString.replaceAll(regexPattern, "");

        return formatted;
    }

    public String getMovieNameFromNode(String unformatted) {
        var formatted = getFormattedNameNode(unformatted);

        final var regexSplit = "\\dD";
        var splits = formatted.split(regexSplit);

        return splits[0].trim();
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
