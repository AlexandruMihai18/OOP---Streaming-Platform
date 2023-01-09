package actions;

import database.*;
import fileio.ActionInput;
import helpers.Constants;
import observer.MyObservable;
import observer.MyObserver;
import server.Navigator;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Observable class that will send notification for observers (users)
 */
public final class DatabaseAdd extends ActionStrategy implements MyObservable {
    private String feature;
    private Movie addedMovie;
    private ArrayList<MyObserver> observers = new ArrayList<>();

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
         * Set the notification
         */
        Notification notification = new Notification(addedMovie.getName(),
                Constants.ADD_NOTIFICATION);

        /**
         * Set the observers and notify them
         */
        setObservers(UsersDatabase.getInstance().getUsers());
        notifyObservers(notification);

        /**
         * Adding the movie to the movie Database
         */
        MoviesDatabase.getInstance().getMovies().add(addedMovie);
    }

    /**
     * Filter the potential observers based on their country of origin and preferred genres
     * @param potentialObservers users that could be observers
     */
    @Override
    public void setObservers(final ArrayList<User> potentialObservers) {
        for (User user : potentialObservers) {
            if (addedMovie.getCountriesBanned().contains(user.getCredentials().getCountry())) {
                continue;
            }

            for (String genre : user.getSubscribedGenres()) {
                if (addedMovie.checkMovieByGenre(Arrays.asList(genre))) {
                    observers.add(user);
                    break;
                }
            }
        }
    }

    /**
     * Notify all observers
     * @param arg notification
     */
    @Override
    public void notifyObservers(final Object arg) {
        observers.forEach(observers -> observers.update(this, arg));
    }
}
