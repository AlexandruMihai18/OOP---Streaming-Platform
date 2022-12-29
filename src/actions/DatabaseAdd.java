package actions;

import database.*;
import fileio.ActionInput;
import helpers.Constants;
import server.Navigator;

import java.util.ArrayList;
import java.util.Arrays;

public final class DatabaseAdd extends ActionStrategy implements Notify {
    private String feature;
    private Movie addedMovie;

    public DatabaseAdd(final ActionInput action) {
        super(action.getType());
        feature = action.getFeature();
        addedMovie = new Movie(action.getAddedMovie());
    }

    @Override
    public void actionStrategy(final Navigator navigator) {
        /**
         * Checking if the movie is already in the movie Database
         */
        if (getMovie(MoviesDatabase.getInstance().getMovies(), addedMovie.getName()) != null) {
            setError();
            return;
        }

        /**
         * Notifying the users
         */
        Notification notification = new Notification(addedMovie.getName(),
                Constants.ADD_NOTIFICATION);

        notifyUsers(UsersDatabase.getInstance().getUsers(), notification);

        /**
         * Adding the movie to the movie Database
         */
        MoviesDatabase.getInstance().getMovies().add(addedMovie);
    }

    @Override
    public void notifyUsers(final ArrayList<User> users, final Notification notification) {
        for (User user : users) {
            if (addedMovie.getCountriesBanned().contains(user.getCredentials().getCountry())) {
                continue;
            }

            for (String genre : user.getSubscribedGenres()) {
                if (addedMovie.checkMovieByGenre(Arrays.asList(genre))) {
                    user.getNotifications().add(notification);
                    break;
                }
            }
        }
    }
}
