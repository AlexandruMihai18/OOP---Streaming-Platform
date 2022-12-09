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
        if (!navigator.getCurrentPage().checkAction(feature)) {
            setOutput("Error", new Navigator());
            return;
        }

        Movie purchasedMovie = navigator.getCurrentMovies().get(0);

        if (!navigator.getCurrentUser().getPurchasedMovies().contains(purchasedMovie)) {
            setOutput("Error", new Navigator());
            return;
        }

        navigator.getCurrentUser().getWatchedMovies().add(purchasedMovie);
        setOutput(null, navigator);
    }
}
