package actions;

import database.Movie;
import fileio.ActionInput;
import pages.Page;
import server.Navigator;

import java.util.ArrayList;

public final class ChangePageMovieAction extends Action {
    private String page;
    private String movie;

    public ChangePageMovieAction(final ActionInput action) {
        super(action.getType());
        page = action.getPage();
        movie = action.getMovie();
    }

    @Override
    public void actionStrategy(final Navigator navigator) {
        /**
         * Check if the page is valid
         */
        if (!navigator.getCurrentPage().checkNextPage(page)) {
            setError();
            return;
        }

        Movie currentMovie = getMovie(navigator.getCurrentPage().getCurrentMovies(), movie);

        /**
         * Display error if the searched movie does not exist
         */
        if (currentMovie == null) {
            setError();
            return;
        }

        Page previousPage = navigator.getCurrentPage();

        /**
         * Adding the previous page (movie page) to allPages array for back feature
         */
        navigator.getAllPages().add(previousPage);

        navigator.setCurrentPage(navigator.getCurrentPage().goToNextPage(page));
        navigator.getCurrentPage().setCurrentUser(previousPage.getCurrentUser());
        navigator.getCurrentPage().setMovieName(movie);
        navigator.getCurrentPage().setCurrentMovies(new ArrayList<>());
        navigator.getCurrentPage().getCurrentMovies().add(currentMovie);

        setOutput(navigator);
    }
}
