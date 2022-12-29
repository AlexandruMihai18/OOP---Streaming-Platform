package actions;

import database.Filters;
import database.Movie;
import fileio.ActionInput;
import helpers.Constants;
import server.Navigator;

import java.util.ArrayList;

public final class OnPageFilters extends ActionStrategy {
    private String feature;
    private Filters filters;

    public OnPageFilters(final ActionInput action) {
        super(action.getType());
        feature = action.getFeature();
        filters = new Filters(action.getFilters());
    }

    @Override
    public void actionStrategy(final Navigator navigator) {
        /**
         * Check the features for this page
         */
        if (!navigator.getCurrentPage().checkAction(feature)) {
            setError();
            return;
        }

        /**
         * Get movies by given filters
         */
        ArrayList<Movie> allMoviesFromPage = navigator.getCurrentPage().getAllMoviesFromPage();

        ArrayList<Movie> searchMovies = getMoviesByFilters(allMoviesFromPage);
        navigator.getCurrentPage().setCurrentMovies(searchMovies);

        setOutput(navigator);
    }

    /**
     * Sort movies by rating and duration using INCREASING or DECREASING key word
     * Display movies that contain a certain actor(s) or is from a specific genre(s)
     * @param unfilteredMovies available movies, before applying filters
     * @return filtered movies
     */
    public ArrayList<Movie> getMoviesByFilters(final ArrayList<Movie> unfilteredMovies) {
        ArrayList<Movie> moviesCopy = new ArrayList<>(unfilteredMovies);

        if (filters.getSort() != null) {
            if (filters.getSort().getRating() != null) {
                if (filters.getSort().getRating().equals(Constants.DECREASING)) {
                    moviesCopy.sort((o1, o2) -> o2.compareByRating(o1));
                } else {
                    moviesCopy.sort((o1, o2) -> o1.compareByRating(o2));
                }
            }

            if (filters.getSort().getDuration() != null) {
                if (filters.getSort().getDuration().equals(Constants.DECREASING)) {
                    moviesCopy.sort((o1, o2) -> o2.compareByDuration(o1));
                } else {
                    moviesCopy.sort((o1, o2) -> o1.compareByDuration(o2));
                }
            }
        }

        ArrayList<Movie> movies = new ArrayList<>(moviesCopy);

        if (filters.getContains() != null) {
            if (filters.getContains().getActors() != null) {
                movies.removeIf(movie -> !movie
                        .checkMovieByActors(filters.getContains().getActors()));
            }
            if (filters.getContains().getGenre() != null) {
                movies.removeIf(movie -> !movie
                        .checkMovieByGenre(filters.getContains().getGenre()));
            }
        }

        return movies;
    }
}
