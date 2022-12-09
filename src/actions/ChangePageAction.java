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
        if (!navigator.getCurrentPage().checkNextPage(page)) {
            setOutput("Error", new Navigator());
            return;
        }

        navigator.setCurrentPage(navigator.getCurrentPage().goToNextPage(page));
        navigator.setCurrentMovies(new ArrayList<>());

        if (page.equals(PageEnum.UNAUTHENTICATED_HOMEPAGE)) {
            navigator.setCurrentUser(null);
        }

        if (page.equals(PageEnum.MOVIES_PAGE)) {
            navigator.setCurrentMovies(getVisibleMovies(MoviesDatabase.getInstance().getMovies(),
                    navigator.getCurrentUser().getCredentials().getCountry()));
            navigator.setAllMoviesFromPage(navigator.getCurrentMovies());
            setOutput(null, navigator);
        }
    }

    public ArrayList<Movie> getVisibleMovies(final ArrayList<Movie> movies, final String country) {
        ArrayList<Movie> allowedMovies = new ArrayList<>(movies);
        allowedMovies.removeIf(movie -> movie.getCountriesBanned().contains(country));
        return allowedMovies;
    }
}
