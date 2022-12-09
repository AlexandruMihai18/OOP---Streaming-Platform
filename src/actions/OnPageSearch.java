package actions;

import database.Movie;
import fileio.ActionInput;
import server.Navigator;

import java.util.ArrayList;

public final class OnPageSearch extends Action {
    private String page;
    private String feature;
    private String startsWith;

    public OnPageSearch(final ActionInput action) {
        super(action.getType());
        page = action.getPage();
        feature = action.getFeature();
        startsWith = action.getStartsWith();
    }

    @Override
    public void doAction(final Navigator navigator) {
        if (!navigator.getCurrentPage().checkAction(feature)) {
            setOutput("Error", new Navigator());
            return;
        }

        ArrayList<Movie> searchMovies = getMovies(navigator.getAllMoviesFromPage());
        navigator.setCurrentMovies(searchMovies);

        setOutput(null, navigator);
    }

    public ArrayList<Movie> getMovies(final ArrayList<Movie> movies) {
        ArrayList<Movie> newMovies = new ArrayList<>();
        for (Movie movie : movies) {
            if (movie.getName().startsWith(startsWith)) {
                newMovies.add(movie);
            }
        }
        return newMovies;
    }
}
