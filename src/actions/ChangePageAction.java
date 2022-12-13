package actions;

import database.Movie;
import database.MoviesDatabase;
import fileio.ActionInput;
import helpers.PageEnum;
import server.Navigator;

import java.util.ArrayList;

public final class ChangePageAction extends Action {
    private String page;

    public ChangePageAction(final ActionInput action) {
        super(action.getType());
        page = action.getPage();
    }

    @Override
    public void doAction(final Navigator navigator) {
        /**
         * Check if the next page is valid
         */
        if (!navigator.getCurrentPage().checkNextPage(page)) {
            setError();
            return;
        }

        /**
         * Assign the new page and movies displayed
         */
        navigator.setCurrentPage(navigator.getCurrentPage().goToNextPage(page));
        navigator.setCurrentMovies(new ArrayList<>());

        /**
         * Log out action -- set the current user to null
         */
        if (page.equals(PageEnum.UNAUTHENTICATED_HOMEPAGE)) {
            navigator.setCurrentUser(null);
        }

        /**
         * Change to movies page -- display all available movies
         */
        if (page.equals(PageEnum.MOVIES_PAGE)) {
            navigator.setCurrentMovies(getVisibleMovies(MoviesDatabase.getInstance().getMovies(),
                    navigator.getCurrentUser().getCredentials().getCountry()));
            navigator.setAllMoviesFromPage(navigator.getCurrentMovies());
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
