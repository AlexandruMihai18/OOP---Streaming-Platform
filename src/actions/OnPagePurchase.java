package actions;

import database.Movie;
import database.User;
import fileio.ActionInput;
import helpers.Constants;
import server.Navigator;

public final class OnPagePurchase extends ActionStrategy {
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

        User currentUser = navigator.getCurrentPage().getCurrentUser();

        Movie moviePurchased = getMovie(currentUser.getPurchasedMovies(),
                navigator.getCurrentPage().getMovieName());

        /**
         * Check if the movie has already been purchase
         */
        if (moviePurchased != null) {
            setError();
            return;
        }

        Movie movie = getMovie(navigator.getCurrentPage().getCurrentMovies(),
                navigator.getCurrentPage().getMovieName());

        /**
         * Mark the movie as purchased
         */
        currentUser.getPurchasedMovies().add(movie);

        /**
         * Buy the movie by either using tokens or using a free movie from the premium account
         */
        if (currentUser.getCredentials().getAccountType().equals(Constants.PREMIUM)
                && currentUser.getNumFreePremiumMovies() > 0) {
            currentUser.setNumFreePremiumMovies(currentUser.getNumFreePremiumMovies() - 1);
        } else {
            currentUser.setTokensCount(currentUser.getTokensCount() - 2);
        }

        setOutput(navigator);
    }
}
