package actions;

import database.Movie;
import fileio.ActionInput;
import helpers.Constants;
import server.Navigator;

public final class OnPageRateMovie extends Action {
    private String page;
    private String feature;
    private int rate;

    public OnPageRateMovie(final ActionInput action) {
        super(action.getType());
        page = action.getPage();
        feature = action.getFeature();
        rate = action.getRate();
    }

    @Override
    public void doAction(final Navigator navigator) {
        /**
         * Check the features for this page
         */
        if (!navigator.getCurrentPage().checkAction(feature)) {
            setError();
            return;
        }

        Movie watchedMovie = navigator.getCurrentMovies().get(0);

        /**
         * Check if the movie has been watch
         */
        if (!navigator.getCurrentUser().getWatchedMovies().contains(watchedMovie)) {
            setError();
            return;
        }

        /**
         * Check if the movie has already been rated
         */
        if (navigator.getCurrentUser().getRatedMovies().contains(watchedMovie)) {
            setError();
            return;
        }

        /**
         * Check if the rate exceeds the max rate available
         */
        if (rate > Constants.MAX_RATE) {
            setError();
            return;
        }

        /**
         * Mark the movie as rated and recalculate the rating
         */
        navigator.getCurrentUser().getRatedMovies().add(watchedMovie);
        watchedMovie.setNumRatings(watchedMovie.getNumRatings() + 1);
        watchedMovie.setTotalRating(watchedMovie.getTotalRating() + rate);
        watchedMovie.setRating((float) watchedMovie.getTotalRating()
                / (float) watchedMovie.getNumRatings());

        setOutput(navigator);
    }
}
