package actions;

import database.Movie;
import fileio.ActionInput;
import helpers.Constants;
import server.Navigator;

public final class OnPageRateMovie extends Action {
    private String feature;
    private int rate;

    public OnPageRateMovie(final ActionInput action) {
        super(action.getType());
        feature = action.getFeature();
        rate = action.getRate();
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

        Movie watchedMovie = getMovie(navigator.getCurrentPage().getCurrentUser().getWatchedMovies(),
                navigator.getCurrentPage().getMovieName());

        /**
         * Check if the movie has been watch
         */
        if (watchedMovie == null) {
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

        Movie ratedMovie = getMovie(navigator.getCurrentPage().getCurrentUser().getRatedMovies(),
                navigator.getCurrentPage().getMovieName());

        Movie asynchronousMovie = getMovie(navigator.getCurrentPage().getCurrentUser().getAsynchronousMovies(),
                navigator.getCurrentPage().getMovieName());

        /**
         * Check if the movie has already been rated and adjusting the rating
         */
        if (asynchronousMovie != null) {
            ratedMovie.setTotalRating(ratedMovie.getTotalRating() - asynchronousMovie.getPersonalRating());
            asynchronousMovie.setPersonalRating(rate);
            ratedMovie.setTotalRating(ratedMovie.getTotalRating() + asynchronousMovie.getPersonalRating());
            ratedMovie.setRating((float) ratedMovie.getTotalRating()
                    / (float) ratedMovie.getNumRatings());

            setOutput(navigator);

            return;
        }

        /**
         * Mark the movie as rated and recalculate the rating
         */

        watchedMovie.setNumRatings(watchedMovie.getNumRatings() + 1);
        watchedMovie.setTotalRating(watchedMovie.getTotalRating() + rate);
        watchedMovie.setRating((float) watchedMovie.getTotalRating()
                / (float) watchedMovie.getNumRatings());

        navigator.getCurrentPage().getCurrentUser().getRatedMovies().add(watchedMovie);

        Movie newAsynchronousMovie = new Movie(watchedMovie);
        newAsynchronousMovie.setPersonalRating(rate);
        navigator.getCurrentPage().getCurrentUser().getAsynchronousMovies().add(newAsynchronousMovie);

        setOutput(navigator);
    }
}
