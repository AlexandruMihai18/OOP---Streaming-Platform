package actions;

import database.Movie;
import fileio.ActionInput;
import server.Navigator;

public final class OnPageLike extends Action {
    private String page;
    private String feature;

    public OnPageLike(final ActionInput action) {
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
        watchedMovie.setNumLikes(watchedMovie.getNumLikes() + 1);

        setOutput(null, navigator);
    }
}
