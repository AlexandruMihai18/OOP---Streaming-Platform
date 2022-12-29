package actions;

import database.Movie;
import database.MoviesDatabase;
import fileio.ActionInput;
import helpers.PageEnum;
import pages.Page;
import server.Navigator;

import java.util.ArrayList;

public final class ChangePageAction extends ActionStrategy {
    private String page;

    public ChangePageAction(final ActionInput action) {
        super(action.getType());
        page = action.getPage();
    }

    @Override
    public void actionStrategy(final Navigator navigator) {
        /**
         * Check if the next page is valid
         */
        if (!navigator.getCurrentPage().checkNextPage(page)) {
            setError();
            return;
        }

        Page previousPage = navigator.getCurrentPage();

        if (navigator.getCurrentPage().getCurrentUser() != null) {
            navigator.getAllPages().add(previousPage);
        }

        /**
         * Assign the new page and movies displayed
         */

        navigator.setCurrentPage(navigator.getCurrentPage().goToNextPage(page));
        navigator.getCurrentPage().setCurrentUser(previousPage.getCurrentUser());
        navigator.getCurrentPage().setCurrentMovies(new ArrayList<>());

        /**
         * Log out action -- set the current user to null
         */
        if (page.equals(PageEnum.UNAUTHENTICATED_HOMEPAGE)) {
            navigator.setAllPages(new ArrayList<>());
            navigator.getCurrentPage().setCurrentUser(null);
        }

        Page currentPage = navigator.getCurrentPage();

        /**
         * Change to movies page -- display all available movies
         */
        if (page.equals(PageEnum.MOVIES_PAGE)) {
            currentPage.setCurrentMovies(getVisibleMovies(MoviesDatabase.getInstance().getMovies(),
                    currentPage.getCurrentUser().getCredentials().getCountry()));

            currentPage.setAllMoviesFromPage(currentPage.getCurrentMovies());
            setOutput(navigator);
        }
    }

    /**
     * Select all movies not banned for a user (based on country)
     * @param movies movies from database
     * @param country user's country of origin
     * @return available movies for a user
     */
    public ArrayList<Movie> getVisibleMovies(final ArrayList<Movie> movies, final String country) {
        ArrayList<Movie> allowedMovies = new ArrayList<>(movies);
        allowedMovies.removeIf(movie -> movie.getCountriesBanned().contains(country));
        return allowedMovies;
    }
}
