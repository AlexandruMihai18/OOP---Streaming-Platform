package actions;

import database.Movie;
import fileio.ActionInput;
import server.Navigator;

public class OnPagePurchase extends Action {
    private String page;
    private String feature;

    public OnPagePurchase(ActionInput action) {
        super(action.getType());
        page = action.getPage();
        feature = action.getFeature();
    }

    @Override
    public void doAction(Navigator navigator) {
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
    }
}
