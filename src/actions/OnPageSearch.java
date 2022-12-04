package actions;

import database.Movie;
import database.MoviesDatabase;
import fileio.ActionInput;
import server.Navigator;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class OnPageSearch extends Action {
    private String page;
    private String feature;
    private String startsWith;

    public OnPageSearch(ActionInput action) {
        super(action.getType());
        page = action.getPage();
        feature = action.getFeature();
        startsWith = action.getStartsWith();
    }

    @Override
    public void doAction(Navigator navigator) {
        if (!navigator.getCurrentPage().checkAction(feature)) {
            setOutput("Error", new Navigator());
            return;
        }

        ArrayList<Movie> searchMovies = getMovies();
        ArrayList<Movie> previousMovies = navigator.getCurrentMovies();
        navigator.setCurrentMovies(searchMovies);

        setOutput(null, navigator);
        navigator.setCurrentMovies(previousMovies);
    }

    public ArrayList<Movie> getMovies() {
        ArrayList<Movie> movies = new ArrayList<>();
        for (Movie movie : MoviesDatabase.getInstance().getMovies()) {
            if (movie.getName().startsWith(startsWith)) {
                movies.add(movie);
            }
        }
        return movies;
    }
}
