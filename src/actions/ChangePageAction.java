package actions;

import database.Movie;
import database.MoviesDatabase;
import fileio.ActionInput;
import helpers.PageEnum;
import pages.Page;
import server.Navigator;

import java.util.ArrayList;

public final class ChangePageAction extends Action {
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

        /**
         * Change to movies page -- display all available movies
         */
        if (page.equals(PageEnum.MOVIES_PAGE)) {
            navigator.getCurrentPage().setCurrentMovies(getVisibleMovies(MoviesDatabase.getInstance().getMovies(),
                    navigator.getCurrentPage().getCurrentUser().getCredentials().getCountry()));
            navigator.getCurrentPage().setAllMoviesFromPage(navigator.getCurrentPage().getCurrentMovies());
            setOutput(navigator);
        }

        if (!page.equals(PageEnum.UNAUTHENTICATED_HOMEPAGE) && !page.equals(PageEnum.LOGIN_PAGE) && !page.equals(PageEnum.REGISTER_PAGE)) {
            navigator.getAllPages().add(navigator.getCurrentPage());
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
