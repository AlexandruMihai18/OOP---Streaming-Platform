package actions;

import database.Movie;
import fileio.ActionInput;
import server.Navigator;

public final class OnPageLike extends Action {
    private String feature;

    public OnPageLike(final ActionInput action) {
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

        /**
         * Check if the movie has been watched
         */
        Movie watchedMovie = navigator.getCurrentPage().getCurrentMovies().get(0);
        if (!navigator.getCurrentPage().getCurrentUser().getWatchedMovies().contains(watchedMovie)) {
            setError();
            return;
        }

        /**
         * Check if the movie has already been liked
         */
        if (navigator.getCurrentPage().getCurrentUser().getLikedMovies().contains(watchedMovie)) {
            setError();
            return;
        }

        /**
         * Mark the movie as liked
         */
        navigator.getCurrentPage().getCurrentUser().getLikedMovies().add(watchedMovie);
        watchedMovie.setNumLikes(watchedMovie.getNumLikes() + 1);

        setOutput(navigator);
    }
}
