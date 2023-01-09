package actions;

import database.*;
import helpers.Constants;
import observer.MyObservable;
import observer.MyObserver;
import server.Navigator;

import java.util.*;

public final class Recommendation extends ActionStrategy implements MyObservable {
    public Recommendation() {

    }

    private ArrayList<MyObserver> observers = new ArrayList<>();


    private final class LikedGenre implements Comparable<LikedGenre> {
        private String genre;
        private int numLikes;

        private LikedGenre(final String genre, final int numLikes) {
            this.genre = genre;
            this.numLikes = numLikes;
        }


        @Override
        public int compareTo(final LikedGenre likedGenre) {
            if (numLikes == likedGenre.numLikes) {
                return genre.compareTo(likedGenre.genre);
            }
            return numLikes - likedGenre.numLikes;
        }
    }

    @Override
    public void actionStrategy(final Navigator navigator) {
        User currentUser = navigator.getCurrentPage().getCurrentUser();

        /**
         * Obtain the liked genres for a user and sorting them by number of likes
         */
        ArrayList<LikedGenre> likedGenres = sortLikedGenres(currentUser.getLikedMovies());

        ArrayList<Movie> movies = getVisibleMovies(MoviesDatabase.getInstance().getMovies(),
                currentUser.getCredentials().getCountry());

        Collections.sort(movies, Movie::compareByNumLikes);

        /**
         * Set the notification
         */
        Notification notification = new Notification(null, Constants.RECOMMENDATION_NOTIFICATION);

        ArrayList<User> notifiedUsers = new ArrayList<>();
        notifiedUsers.add(currentUser);

        /**
         * Set the notified users
         */
        setObservers(notifiedUsers);

        navigator.getCurrentPage().setCurrentMovies(null);

        /**
         * Find the first movie not watched that contains the most liked genre
         * and notify the observers
         */
        for (LikedGenre likedGenre : likedGenres) {
            for (Movie movie : movies) {
                if (!currentUser.getWatchedMovies().contains(movie)
                        && hasGenre(movie, likedGenre.genre)) {
                    notification.setMovieName(movie.getName());
                    notifyObservers(notification);
                    setOutput(navigator);
                    return;
                }
            }
        }

        /**
         * No movie found -- set notification accordingly
         */
        notification.setMovieName(Constants.NO_RECOMMENDATION);
        notifyObservers(notification);

        setOutput(navigator);
    }

    /**
     * Add all potential observers to the actual observers list
     * @param potentialObservers users that could be observers
     */
    @Override
    public void setObservers(final ArrayList<User> potentialObservers) {
        for (User user : potentialObservers) {
            observers.add(user);
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

    /**
     * Building a list of the liked genres based on a liked movie list and sorting it according to
     * the number of likes
     * @param movies liked movie list
     * @return ordered liked genres list
     */
    public ArrayList<LikedGenre> sortLikedGenres(final ArrayList<Movie> movies) {
        boolean foundGenre;

        ArrayList<LikedGenre> likedGenres = new ArrayList<>();

        for (Movie movie : movies) {
            for (String genre : movie.getGenres()) {
                foundGenre = false;
                for (LikedGenre likedGenre : likedGenres) {
                    /**
                     * Incrementing the number of likes for an already existing genre
                     */
                    if (likedGenre.genre.equals(genre)) {
                        likedGenre.numLikes++;
                        foundGenre = true;
                    }
                }

                /**
                 * Adding the genre in case this is its first appearance
                 */
                if (!foundGenre) {
                    likedGenres.add(new LikedGenre(genre, 1));
                }
            }
        }

        Collections.sort(likedGenres);

        return likedGenres;
    }

    /**
     * Select all movies not banned for a user (based on country)
     * @param movies movies from database
     * @param country user's country of origin
     * @return available movies for a user
     */
    public ArrayList<Movie> getVisibleMovies(final ArrayList<Movie> movies, final String country) {
        ArrayList<Movie> allowedMovies = new ArrayList<>(movies);
        allowedMovies.removeIf(movie -> movie.getCountriesBanned().contains(country));
        return allowedMovies;
    }

    /**
     * Checking if a movie contains a certain genre
     * @param movie movie to check
     * @param genre inquired genre
     * @return true -- the movie contains the genre, false -- otherwise
     */
    public boolean hasGenre(final Movie movie, final String genre) {
        ArrayList<String> genres = new ArrayList<>();

        genres.add(genre);

        return movie.checkMovieByGenre(genres);
    }
}
