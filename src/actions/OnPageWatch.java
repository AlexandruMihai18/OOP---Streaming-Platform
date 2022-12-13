package actions;

import database.Movie;
import fileio.ActionInput;
import server.Navigator;

public final class OnPageWatch extends Action {
    private String page;
    private String feature;

    public OnPageWatch(final ActionInput action) {
        super(action.getType());
        page = action.getPage();
        feature = action.getFeature();
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

        Movie purchasedMovie = navigator.getCurrentMovies().get(0);

        /**
         * Check if the movie has been purchased
         */
        if (!navigator.getCurrentUser().getPurchasedMovies().contains(purchasedMovie)) {
            setError();
            return;
        }

        navigator.getCurrentUser().getWatchedMovies().add(purchasedMovie);
        setOutput(navigator);
    }
}
