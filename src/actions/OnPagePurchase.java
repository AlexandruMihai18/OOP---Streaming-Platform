package actions;

import database.Movie;
import fileio.ActionInput;
import server.Navigator;

public final class OnPagePurchase extends Action {
    private String page;
    private String feature;

    public OnPagePurchase(final ActionInput action) {
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

        Movie movie = navigator.getCurrentMovies().get(0);

        /**
         * Check if the movie has already been purchase
         */
        if (navigator.getCurrentUser().getPurchasedMovies().contains(movie)) {
            setError();
            return;
        }

        /**
         * Mark the movie as purchased
         */
        navigator.getCurrentUser().getPurchasedMovies().add(movie);

        /**
         * Buy the movie by either using tokens or using a free movie from the premium account
         */
        if (navigator.getCurrentUser().getCredentials().getAccountType().equals("premium")
                && navigator.getCurrentUser().getNumFreePremiumMovies() > 0) {
            navigator.getCurrentUser().setNumFreePremiumMovies(
                    navigator.getCurrentUser().getNumFreePremiumMovies() - 1);
        } else {
            navigator.getCurrentUser().setTokensCount(
                    navigator.getCurrentUser().getTokensCount() - 2);
        }

        setOutput(navigator);
    }
}
