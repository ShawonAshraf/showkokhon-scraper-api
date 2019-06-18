package com.showkokhon.scraper.showkokhonscraper.utils;

import com.showkokhon.scraper.showkokhonscraper.model.Movie;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class ListMerger {
    public static ArrayList<Movie> simpleMerge(ArrayList<Movie> a, ArrayList<Movie> b) {
        var merged = new ArrayList<Movie>();

        merged.addAll(a);
        merged.addAll(b);

        return merged;
    }

    public static ArrayList<Movie> mergeLists(ArrayList<Movie> a, ArrayList<Movie> b) {
        var merged = new ArrayList<Movie>();

        ArrayList<Movie> pivot = !a.isEmpty() ? a : b;
        ArrayList<Movie> nonPivot = !a.isEmpty() ? b : a;

        merged.addAll(pivot);

        int s = merged.size();
        for (int i = 0; i < s; i++) {
            var movieToUpdate = merged.get(i);
            var name = movieToUpdate.getName();

            /**
             * get the movies with the same name
             */
            var moviesWithSameName = nonPivot.stream()
                    .filter(movie -> movie.getName().equals(name))
                    .collect(Collectors.toList());


            int l = movieToUpdate.getSchedule().size();
            for (Integer j = 0; j < l; j++) {
                final var updateIndex = j; // to avoid lambda restrictions

                // date of the movie to update
                var referenceDate = movieToUpdate.getSchedule().get(j).getDate();

                // iterate through
                moviesWithSameName.forEach(movie -> {
                    movie.getSchedule().forEach(schedule -> {
                        var date = schedule.getDate();
                        var playingAt = schedule.getPlayingAt();

                        if (date.equals(referenceDate)) {
                            // date matches, so update
                            movieToUpdate.getSchedule().get(updateIndex).getPlayingAt().addAll(playingAt);
                        } else {
                            // add a new entry
                            movieToUpdate.getSchedule().add(schedule);
                        }
                    });
                });
            }

        }

        return merged;
    }
}
