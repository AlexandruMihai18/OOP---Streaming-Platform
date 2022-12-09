package actions;

import database.Movie;
import fileio.ActionInput;
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
    public void doAction(final Navigator navigator) {
        if (!navigator.getCurrentPage().checkNextPage(page)) {
            setOutput("Error", new Navigator());
            return;
        }

        Movie currentMovie = getMovie(navigator.getCurrentMovies());

        if (currentMovie == null) {
            setOutput("Error", new Navigator());
            return;
        }

        navigator.setCurrentPage(navigator.getCurrentPage().goToNextPage(page));
        navigator.setCurrentMovies(new ArrayList<>());
        navigator.getCurrentMovies().add(currentMovie);

        setOutput(null, navigator);
    }

    public Movie getMovie(final ArrayList<Movie> movies) {
        for (Movie movie : movies) {
            if (movie.getName().equals(this.movie)) {
                return movie;
            }
        }
        return null;
    }
}
