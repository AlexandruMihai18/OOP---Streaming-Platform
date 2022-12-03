package actions;

import database.Movie;
import database.UsersDatabase;
import fileio.ActionInput;
import server.Navigator;

public class OnPageLike extends Action {
    private String page;
    private String feature;

    public OnPageLike(ActionInput action) {
        super(action.getType());
        page = action.getPage();
        feature = action.getFeature();
    }

    @Override
    public void doAction(Navigator navigator) {
        if (navigator.getCurrentMovies() == null) {
            setOutput("Error", new Navigator());
            return;
        }

        Movie watchedMovie = navigator.getCurrentMovies().get(0);
        if (!navigator.getCurrentUser().getWatchedMovies().contains(watchedMovie)) {
            setOutput("Error", new Navigator());
            return;
        }

        if (navigator.getCurrentUser().getLikedMovies().contains(watchedMovie)) {
            setOutput("Error", new Navigator());
            return;
        }

        navigator.getCurrentUser().getLikedMovies().add(watchedMovie);
    }
}
