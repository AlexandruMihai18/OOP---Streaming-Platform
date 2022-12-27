package actions;

import database.Movie;
import fileio.ActionInput;
import server.Navigator;

public final class OnPagePurchase extends Action {
    private String feature;

    public OnPagePurchase(final ActionInput action) {
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

        Movie movie = navigator.getCurrentPage().getCurrentMovies().get(0);

        /**
         * Check if the movie has already been purchase
         */
        if (navigator.getCurrentPage().getCurrentUser().getPurchasedMovies().contains(movie)) {
            setError();
            return;
        }

        /**
         * Mark the movie as purchased
         */
        navigator.getCurrentPage().getCurrentUser().getPurchasedMovies().add(movie);

        /**
         * Buy the movie by either using tokens or using a free movie from the premium account
         */
        if (navigator.getCurrentPage().getCurrentUser().getCredentials().getAccountType().equals("premium")
                && navigator.getCurrentPage().getCurrentUser().getNumFreePremiumMovies() > 0) {
            navigator.getCurrentPage().getCurrentUser().setNumFreePremiumMovies(
                    navigator.getCurrentPage().getCurrentUser().getNumFreePremiumMovies() - 1);
        } else {
            navigator.getCurrentPage().getCurrentUser().setTokensCount(
                    navigator.getCurrentPage().getCurrentUser().getTokensCount() - 2);
        }

        setOutput(navigator);
    }
}
