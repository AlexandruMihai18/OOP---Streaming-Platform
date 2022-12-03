package actions;

import database.Filters;
import database.Movie;
import fileio.ActionInput;
import server.Navigator;

import java.util.ArrayList;

public class OnPageFilters extends Action {
    private String page;
    private String feature;
    private Filters filters;

    public OnPageFilters(ActionInput action) {
        super(action.getType());
        page = action.getPage();
        feature = action.getFeature();
        filters = new Filters(action.getFilters());
    }

    @Override
    public void doAction(Navigator navigator) {
        if (!navigator.getCurrentPage().checkAction(feature)) {
            setOutput("Error", new Navigator());
            return;
        }

        ArrayList<Movie> searchMovies = getMoviesByFilters();
        ArrayList<Movie> previousMovies = navigator.getCurrentMovies();
        navigator.setCurrentMovies(searchMovies);

        setOutput(null, navigator);
        navigator.setCurrentMovies(previousMovies);
    }

    public ArrayList<Movie> getMoviesByFilters() {
        ArrayList<Movie> movies = new ArrayList<>();
        return movies;
    }
}
