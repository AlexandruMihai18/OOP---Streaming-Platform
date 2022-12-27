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
        Movie watchedMovie = getMovie(navigator
                        .getCurrentPage().getCurrentUser().getWatchedMovies(),
                navigator.getCurrentPage().getMovieName());

        if (watchedMovie == null) {
            setError();
            return;
        }

        Movie likedMovie = getMovie(navigator
                        .getCurrentPage().getCurrentUser().getLikedMovies(),
                navigator.getCurrentPage().getMovieName());

        /**
         * Check if the movie has already been liked
         */
        if (likedMovie != null) {
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
