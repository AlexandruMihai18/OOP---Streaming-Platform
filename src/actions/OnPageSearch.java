package actions;

import database.Movie;
import fileio.ActionInput;
import server.Navigator;

import java.util.ArrayList;

public final class OnPageSearch extends ActionStrategy {
    private String feature;
    private String startsWith;

    public OnPageSearch(final ActionInput action) {
        super(action.getType());
        feature = action.getFeature();
        startsWith = action.getStartsWith();
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

        ArrayList<Movie> searchMovies = getMovies(navigator.getCurrentPage().getAllMoviesFromPage());
        navigator.getCurrentPage().setCurrentMovies(searchMovies);

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
