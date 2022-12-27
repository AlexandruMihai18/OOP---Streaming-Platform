package actions;

import database.*;
import fileio.ActionInput;
import helpers.Constants;
import server.Navigator;

import java.util.ArrayList;

public final class DatabaseDelete extends Action implements Notify{
    private String feature;
    private String deleteMovie;

    public DatabaseDelete(final ActionInput action) {
        super(action.getType());
        feature = action.getFeature();
        deleteMovie = action.getDeleteMovie();
    }

    @Override
    public void actionStrategy(Navigator navigator) {
        Movie movie = getMovie(MoviesDatabase.getInstance().getMovies());

        if (movie == null) {
            setError();
            return;
        }

        Notification notification = new Notification(deleteMovie, Constants.DELETE_NOTIFICATION);

        notifyUsers(UsersDatabase.getInstance().getUsers(), notification);

        MoviesDatabase.getInstance().getMovies().remove(movie);
    }

    public Movie getMovie(final ArrayList<Movie> movies) {
        for (Movie movie : movies) {
            if (movie.getName().equals(this.deleteMovie)) {
                return movie;
            }
        }
        return null;
    }

    @Override
    public void notifyUsers(ArrayList<User> users, Notification notification) {
        Movie movie = getMovie(MoviesDatabase.getInstance().getMovies());

        for (User user : users) {
            if (user.getPurchasedMovies().contains(movie)) {
                user.getPurchasedMovies().remove(movie);
                user.getNotifications().add(notification);
                returnTokens(user);
            }

            if (user.getWatchedMovies().contains(movie)) {
                user.getWatchedMovies().remove(movie);
            }

            if (user.getLikedMovies().contains(movie)) {
                user.getLikedMovies().remove(movie);
            }

            if (user.getRatedMovies().contains(movie)) {
                user.getRatedMovies().remove(movie);
            }
        }
    }

    public void returnTokens(User user) {
        if (user.getCredentials().getAccountType().equals(Constants.PREMIUM)) {
            user.setNumFreePremiumMovies(user.getNumFreePremiumMovies() + 1);
        } else {
            user.setTokensCount(user.getTokensCount() + 2);
        }
    }
}
