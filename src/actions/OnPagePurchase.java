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
        if (!navigator.getCurrentPage().checkAction(feature)) {
            setOutput("Error", new Navigator());
            return;
        }

        Movie movie = navigator.getCurrentMovies().get(0);

        if (navigator.getCurrentUser().getPurchasedMovies().contains(movie)) {
            setOutput("Error", new Navigator());
            return;
        }

        navigator.getCurrentUser().getPurchasedMovies().add(movie);

        if (navigator.getCurrentUser().getCredentials().getAccountType().equals("premium")
                && navigator.getCurrentUser().getNumFreePremiumMovies() > 0) {
            navigator.getCurrentUser().setNumFreePremiumMovies(
                    navigator.getCurrentUser().getNumFreePremiumMovies() - 1);
        } else {
            navigator.getCurrentUser().setTokensCount(
                    navigator.getCurrentUser().getTokensCount() - 2);
        }

        setOutput(null, navigator);
    }
}
