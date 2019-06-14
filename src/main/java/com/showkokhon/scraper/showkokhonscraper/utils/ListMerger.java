package com.showkokhon.scraper.showkokhonscraper.utils;

import com.showkokhon.scraper.showkokhonscraper.model.Movie;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class ListMerger {
    public static ArrayList<Movie> mergeLists(ArrayList<Movie> bcity, ArrayList<Movie> shimantoShambhar) {
        var merged = new ArrayList<Movie>();

        // pivot will serve as the first list to append to merged
        // it may be possible that either of bcity or shimantoShambhar lists are empty due to http errors
        // the non empty list will be marked as pivot and added first to merged

        ArrayList<Movie> pivot = !bcity.isEmpty() ? bcity : shimantoShambhar;
        ArrayList<Movie> nonPivot = !bcity.isEmpty() ? shimantoShambhar : bcity;

        merged.addAll(pivot);

        int s = merged.size();
        for (int i = 0; i < s; i++) {
            var name = merged.get(i).getName();

            /**
             * get the movies with the same name
             */
            var sameMovies = nonPivot.stream().filter(movie -> movie.getName().equals(name)).collect(Collectors.toList());

            if (!sameMovies.isEmpty()) {
                /**
                 * get the same movies
                 */
                int l = sameMovies.size();
                for (int j = 0; j < l; j++) {
                    var sameMovie = sameMovies.get(j);

                    /**
                     * get reference to the movie to merge
                     */
                    var movieToMergeWith = merged.get(i);

                    sameMovie.getLocationWiseTimes().forEach((date, loc) -> {
                        var map = movieToMergeWith.getLocationWiseTimes();

                        /**
                         * check if date exists
                         * if it doesn't, add the loc map
                         */
                        if (map.containsKey(date)) {
                            map.get(date).putAll(loc);
                        } else {
                            map.put(date, loc);
                        }
                    });
                }
            }
        }

        return merged;
    }
}
