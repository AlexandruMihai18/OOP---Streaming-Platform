package actions;

import database.Movie;
import database.MoviesDatabase;
import database.Notification;
import database.User;
import helpers.Constants;
import server.Navigator;

import java.util.*;

public final class Recommendation extends Action implements Notify {
    public Recommendation() {

    }

    private class LikedGenre implements Comparable<LikedGenre> {
        private String genre;
        private int numLikes;

        private LikedGenre(String genre, int numLikes) {
            this.genre = genre;
            this.numLikes = numLikes;
        }

        @Override
        public int compareTo(LikedGenre likedGenre) {
            if (numLikes == likedGenre.numLikes) {
                return genre.compareTo(likedGenre.genre);
            }
            return numLikes - likedGenre.numLikes;
        }
    }

    @Override
    public void actionStrategy(final Navigator navigator) {
        User currentUser = navigator.getCurrentPage().getCurrentUser();

        ArrayList<LikedGenre> likedGenres = sortLikedGenres(currentUser.getLikedMovies());

        ArrayList<Movie> movies = getVisibleMovies(MoviesDatabase.getInstance().getMovies(),
                currentUser.getCredentials().getCountry());

        Collections.sort(movies, Movie::compareByNumLikes);

        Notification notification = new Notification(null, Constants.RECOMMENDATION_NOTIFICATION);

        ArrayList<User> notifiedUsers = new ArrayList<>();
        notifiedUsers.add(currentUser);

        navigator.getCurrentPage().setCurrentMovies(null);

        for (LikedGenre likedGenre : likedGenres) {
            for (Movie movie : movies) {
                if (!currentUser.getWatchedMovies().contains(movie)
                        && hasGenre(movie, likedGenre.genre)) {
                    notification.setMovieName(movie.getName());
                    notifyUsers(notifiedUsers, notification);
                    setOutput(navigator);
                    return;
                }
            }
        }

        notification.setMovieName("No recommendation");
        notifyUsers(notifiedUsers, notification);

        setOutput(navigator);
    }

    public ArrayList<LikedGenre> sortLikedGenres(final ArrayList<Movie> movies) {
        boolean foundGenre;

        ArrayList<LikedGenre> likedGenres = new ArrayList<>();

        for (Movie movie : movies) {
            for (String genre : movie.getGenres()) {
                foundGenre = false;
                for (LikedGenre likedGenre : likedGenres) {
                    if (likedGenre.genre.equals(genre)) {
                        likedGenre.numLikes++;
                        foundGenre = true;
                    }
                }

                if (foundGenre == false) {
                    likedGenres.add(new LikedGenre(genre, 1));
                }
            }
        }

        Collections.sort(likedGenres);

        return likedGenres;
    }

    public ArrayList<Movie> getVisibleMovies(final ArrayList<Movie> movies, final String country) {
        ArrayList<Movie> allowedMovies = new ArrayList<>(movies);
        allowedMovies.removeIf(movie -> movie.getCountriesBanned().contains(country));
        return allowedMovies;
    }

    public boolean hasGenre(Movie movie, String genre) {
        ArrayList<String> genres = new ArrayList<>();

        genres.add(genre);

        return movie.checkMovieByGenre(genres);
    }

    @Override
    public void notifyUsers(ArrayList<User> users, Notification notification) {
        for (User user : users) {
            user.getNotifications().add(notification);
        }
    }
}
