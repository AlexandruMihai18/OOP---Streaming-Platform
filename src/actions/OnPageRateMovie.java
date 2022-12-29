package actions;

import database.Movie;
import database.User;
import fileio.ActionInput;
import helpers.Constants;
import server.Navigator;

public final class OnPageRateMovie extends ActionStrategy {
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

        User currentUser = navigator.getCurrentPage().getCurrentUser();

        Movie watchedMovie = getMovie(currentUser.getWatchedMovies(),
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

        Movie ratedMovie = getMovie(currentUser.getRatedMovies(),
                navigator.getCurrentPage().getMovieName());

        Movie asynchronousMovie = getMovie(currentUser.getAsynchronousMovies(),
                navigator.getCurrentPage().getMovieName());

        /**
         * Check if the movie has already been rated and adjusting the rating
         */
        if (asynchronousMovie != null) {
            int oldRate = asynchronousMovie.getPersonalRating();

            ratedMovie.setTotalRating(ratedMovie.getTotalRating() - oldRate);
            asynchronousMovie.setPersonalRating(rate);
            ratedMovie.setTotalRating(ratedMovie.getTotalRating() + rate);
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

        currentUser.getRatedMovies().add(watchedMovie);

        /**
         * Adding the movie to an asynchronous rating list to be able to change the
         * rating at a later update
         */
        Movie newAsynchronousMovie = new Movie(watchedMovie);
        newAsynchronousMovie.setPersonalRating(rate);
        currentUser.getAsynchronousMovies().add(newAsynchronousMovie);

        setOutput(navigator);
    }
}
