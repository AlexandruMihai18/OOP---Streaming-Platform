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

        Movie purchasedMovie = navigator.getCurrentPage().getCurrentMovies().get(0);

        /**
         * Check if the movie has been purchased
         */
        if (!navigator.getCurrentPage().getCurrentUser().getPurchasedMovies().contains(purchasedMovie)) {
            setError();
            return;
        }

        navigator.getCurrentPage().getCurrentUser().getWatchedMovies().add(purchasedMovie);
        setOutput(navigator);
    }
}
