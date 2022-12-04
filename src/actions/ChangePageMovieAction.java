package actions;

import database.Movie;
import database.MoviesDatabase;
import fileio.ActionInput;
import server.Navigator;

import java.util.ArrayList;

public final class ChangePageMovieAction extends Action {
    private String page;
    private String movie;

    public ChangePageMovieAction(ActionInput action) {
        super(action.getType());
        page = action.getPage();
        movie = action.getMovie();
    }

    @Override
    public void doAction(Navigator navigator) {
        if (!navigator.getCurrentPage().checkNextPage(page)) {
            setOutput("Error", new Navigator());
            return;
        }

        Movie movie = getMovie();

        if (movie == null) {
            setOutput("Error", new Navigator());
            return;
        }

        navigator.setCurrentPage(navigator.getCurrentPage().goToNextPage(page));
        navigator.setCurrentMovies(new ArrayList<>());
        navigator.getCurrentMovies().add(movie);

        setOutput(null, navigator);
    }

    public Movie getMovie() {
        for (Movie movie : MoviesDatabase.getInstance().getMovies()) {
            if (movie.getName().equals(this.movie)) {
                return movie;
            }
        }
        return null;
    }
}
