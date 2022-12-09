package actions;

import database.Filters;
import database.Movie;
import fileio.ActionInput;
import helpers.ActionsEnum;
import server.Navigator;

import java.util.ArrayList;

public final class OnPageFilters extends Action {
    private String page;
    private String feature;
    private Filters filters;

    public OnPageFilters(final ActionInput action) {
        super(action.getType());
        page = action.getPage();
        feature = action.getFeature();
        filters = new Filters(action.getFilters());
    }

    @Override
    public void doAction(final Navigator navigator) {
        if (!navigator.getCurrentPage().checkAction(feature)) {
            setOutput("Error", new Navigator());
            return;
        }

        ArrayList<Movie> searchMovies = getMoviesByFilters(navigator.getAllMoviesFromPage());
        navigator.setCurrentMovies(searchMovies);

        setOutput(null, navigator);
    }

    public ArrayList<Movie> getMoviesByFilters(final ArrayList<Movie> unfilteredMovies) {
        ArrayList<Movie> moviesCopy = new ArrayList<>(unfilteredMovies);

        if (filters.getSort() != null) {
            if (filters.getSort().getRating() != null) {
                if (filters.getSort().getRating().equals(ActionsEnum.DECREASING)) {
                    moviesCopy.sort((o1, o2) -> compareByRating(o2, o1));
                } else {
                    moviesCopy.sort((o1, o2) -> compareByRating(o1, o2));
                }
            }

            if (filters.getSort().getDuration() != null) {
                if (filters.getSort().getDuration().equals(ActionsEnum.DECREASING)) {
                    moviesCopy.sort((o1, o2) -> compareByDuration(o2, o1));
                } else {
                    moviesCopy.sort((o1, o2) -> compareByDuration(o1, o2));
                }
            }
        }

        ArrayList<Movie> movies = new ArrayList<>(moviesCopy);

        if (filters.getContains() != null) {
            if (filters.getContains().getActors() != null) {
                movies.removeIf(movie -> !checkMovieByActors(movie,
                        filters.getContains().getActors()));
            }
            if (filters.getContains().getGenre() != null) {
                movies.removeIf(movie -> !checkMovieByGenre(movie,
                        filters.getContains().getGenre()));
            }
        }

        return movies;
    }

    public int compareByRating(final Object o1, final Object o2) {
        Movie movie1 = (Movie) o1;
        Movie movie2 = (Movie) o2;

        return Float.compare(movie1.getRating(), movie2.getRating());
    }

    public int compareByDuration(final Object o1, final Object o2) {
        Movie movie1 = (Movie) o1;
        Movie movie2 = (Movie) o2;

        return Integer.compare(movie1.getDuration(), movie2.getDuration());
    }

    public boolean checkMovieByActors(final Movie movie, final ArrayList<String> actors) {
        for (String actor : actors) {
            if (!movie.getActors().contains(actor)) {
                return false;
            }
        }
        return true;
    }

    public boolean checkMovieByGenre(final Movie movie, final ArrayList<String> genres) {
        for (String genre : genres) {
            if (!movie.getGenres().contains(genre)) {
                return false;
            }
        }
        return true;
    }

}
