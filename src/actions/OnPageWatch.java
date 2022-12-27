package actions;

import database.Movie;
import fileio.ActionInput;
import server.Navigator;

public final class OnPageWatch extends Action {
    private String feature;

    public OnPageWatch(final ActionInput action) {
        super(action.getType());
        feature = action.getFeature();
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

        Movie purchasedMovie = getMovie(navigator.getCurrentPage().getCurrentUser().getPurchasedMovies(),
                navigator.getCurrentPage().getMovieName());

        /**
         * Check if the movie has been purchased
         */
        if (purchasedMovie == null) {
            setError();
            return;
        }

        Movie watchedMovie = getMovie(navigator.getCurrentPage().getCurrentUser().getWatchedMovies(),
                navigator.getCurrentPage().getMovieName());

        if (watchedMovie != null) {
            return;
        }

        navigator.getCurrentPage().getCurrentUser().getWatchedMovies().add(purchasedMovie);
        setOutput(navigator);
    }
}
