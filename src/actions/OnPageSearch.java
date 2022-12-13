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
        /**
         * Check the features for this page
         */
        if (!navigator.getCurrentPage().checkAction(feature)) {
            setError();
            return;
        }

        ArrayList<Movie> searchMovies = getMovies(navigator.getAllMoviesFromPage());
        navigator.setCurrentMovies(searchMovies);

        setOutput(navigator);
    }

    /**
     * Find the movies starting with the given prefix
     * @param movies available movies
     * @return movies starting with a given prefix
     */
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
