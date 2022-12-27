package actions;

import database.*;
import fileio.ActionInput;
import helpers.Constants;
import server.Navigator;

import java.util.ArrayList;

public final class DatabaseDelete extends Action implements Notify {
    private String feature;
    private String deletedMovie;

    public DatabaseDelete(final ActionInput action) {
        super(action.getType());
        feature = action.getFeature();
        deletedMovie = action.getDeletedMovie();
    }

    @Override
    public void actionStrategy(final Navigator navigator) {
        Movie movie = getMovie(MoviesDatabase.getInstance().getMovies());

        if (movie == null) {
            setError();
            return;
        }

        Notification notification = new Notification(deletedMovie, Constants.DELETE_NOTIFICATION);

        notifyUsers(UsersDatabase.getInstance().getUsers(), notification);

        MoviesDatabase.getInstance().getMovies().remove(movie);
    }

    public Movie getMovie(final ArrayList<Movie> movies) {
        for (Movie movie : movies) {
            if (movie.getName().equals(deletedMovie)) {
                return movie;
            }
        }
        return null;
    }

    @Override
    public void notifyUsers(final ArrayList<User> users, final Notification notification) {

        Movie movie;

        for (User user : users) {

            movie = getMovie(user.getPurchasedMovies(), deletedMovie);

            if (movie != null) {
                user.getPurchasedMovies().remove(movie);
                user.getNotifications().add(notification);
                returnTokens(user);
            }

            movie = getMovie(user.getWatchedMovies(), deletedMovie);

            if (movie != null) {
                user.getWatchedMovies().remove(movie);
            }

            movie = getMovie(user.getLikedMovies(), deletedMovie);

            if (movie != null) {
                user.getLikedMovies().remove(movie);
            }

            movie = getMovie(user.getRatedMovies(), deletedMovie);

            if (movie != null) {
                user.getRatedMovies().remove(movie);
            }
        }
    }

    public void returnTokens(final User user) {
        if (user.getCredentials().getAccountType().equals(Constants.PREMIUM)) {
            user.setNumFreePremiumMovies(user.getNumFreePremiumMovies() + 1);
        } else {
            user.setTokensCount(user.getTokensCount() + 2);
        }
    }
}
