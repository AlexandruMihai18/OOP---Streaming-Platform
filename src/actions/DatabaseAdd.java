package actions;

import database.*;
import fileio.ActionInput;
import helpers.Constants;
import server.Navigator;

import java.util.ArrayList;
import java.util.Arrays;

public final class DatabaseAdd extends Action implements Notify{
    private String feature;
    private Movie addedMovie;

    public DatabaseAdd(final ActionInput action) {
        super(action.getType());
        feature = action.getFeature();
        addedMovie = new Movie(action.getAddedMovie());
    }

    @Override
    public void actionStrategy(final Navigator navigator) {
        if (checkMovie(MoviesDatabase.getInstance().getMovies(), addedMovie)) {
            setError();
            return;
        }

        Notification notification = new Notification(addedMovie.getName(), Constants.ADD_NOTIFICATION);

        notifyUsers(UsersDatabase.getInstance().getUsers(), notification);
    }

    public boolean checkMovie(final ArrayList<Movie> movies, final Movie checkedMovie) {
        for (Movie movie : movies) {
            if (checkedMovie.equals(movie)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void notifyUsers(ArrayList<User> users, Notification notification) {
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
