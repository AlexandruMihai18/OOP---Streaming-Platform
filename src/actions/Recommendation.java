package actions;

import database.Movie;
import database.Notification;
import database.User;
import server.Navigator;

import java.util.*;

public final class Recommendation extends Action implements Notify {
    public Recommendation() {

    }

    @Override
    public void actionStrategy(Navigator navigator) {
        HashMap<String, Integer> likedGenres = sortLikedGenres(navigator.getCurrentPage().getCurrentUser().getLikedMovies());
    }

    public HashMap<String, Integer> sortLikedGenres(ArrayList<Movie> movies) {
        HashMap<String, Integer> likedGenres = new HashMap<>();
        for (Movie movie : movies) {
            for (String genre : movie.getGenres()) {
                if (likedGenres.containsKey(genre)) {
                    likedGenres.put(genre, likedGenres.get(genre) + 1);
                } else {
                    likedGenres.put(genre, 1);
                }
            }
        }

        return likedGenres;
    }

    @Override
    public void notifyUsers(ArrayList<User> users, Notification notification) {

    }
}
