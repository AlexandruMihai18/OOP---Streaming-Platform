package actions;

import database.Movie;
import database.User;
import fileio.ActionInput;
import server.Navigator;

public final class OnPageWatch extends ActionStrategy {
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

        User currentUser = navigator.getCurrentPage().getCurrentUser();

        Movie purchasedMovie = getMovie(currentUser.getPurchasedMovies(),
                navigator.getCurrentPage().getMovieName());

        /**
         * Check if the movie has been purchased
         */
        if (purchasedMovie == null) {
            setError();
            return;
        }

        Movie watchedMovie = getMovie(currentUser.getWatchedMovies(),
                navigator.getCurrentPage().getMovieName());

        if (watchedMovie != null) {
            return;
        }

        currentUser.getWatchedMovies().add(purchasedMovie);
        setOutput(navigator);
    }
}
