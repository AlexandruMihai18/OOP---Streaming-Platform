package actions;

import database.Movie;
import fileio.ActionInput;
import helpers.MagicNumbers;
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
        if (!navigator.getCurrentPage().checkAction(feature)) {
            setOutput("Error", new Navigator());
            return;
        }

        Movie watchedMovie = navigator.getCurrentMovies().get(0);
        if (!navigator.getCurrentUser().getWatchedMovies().contains(watchedMovie)) {
            setOutput("Error", new Navigator());
            return;
        }

        if (navigator.getCurrentUser().getRatedMovies().contains(watchedMovie)) {
            setOutput("Error", new Navigator());
            return;
        }

        if (rate > MagicNumbers.MAX_RATE) {
            setOutput("Error", new Navigator());
            return;
        }

        navigator.getCurrentUser().getRatedMovies().add(watchedMovie);
        watchedMovie.setNumRatings(watchedMovie.getNumRatings() + 1);
        watchedMovie.setTotalRating(watchedMovie.getTotalRating() + rate);
        watchedMovie.setRating((float) watchedMovie.getTotalRating()
                / (float) watchedMovie.getNumRatings());

        setOutput(null, navigator);
    }
}
