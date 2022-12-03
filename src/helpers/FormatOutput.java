package helpers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import database.Movie;
import database.User;
import server.Navigator;

import java.util.ArrayList;

public final class FormatOutput {
    private static ObjectMapper mapper = new ObjectMapper();

    private FormatOutput() {

    }

    public static ObjectNode formatMovie(Movie movie) {
        ObjectNode movieNode = mapper.createObjectNode();
        movieNode.putPOJO("name", movie.getName());
        movieNode.putPOJO("year", movie.getYear());
        movieNode.putPOJO("duration", movie.getDuration());
        movieNode.putPOJO("genres", movie.getGenres());
        movieNode.putPOJO("actors", movie.getActors());
        movieNode.putPOJO("countriesBanned", movie.getCountriesBanned());
        movieNode.putPOJO("numLikes", movie.getNumLikes());
        movieNode.putPOJO("rating", movie.getRating());
        movieNode.putPOJO("numRatings", movie.getNumRatings());
        return movieNode;
    }

    public static ObjectNode formatUser(User user) {
        ObjectNode userNode = mapper.createObjectNode();
        userNode.putPOJO("credentials", user.getCredentials());
        userNode.putPOJO("tokensCount", user.getTokensCount());
        userNode.putPOJO("numFreePremiumMovies", user.getNumFreePremiumMovies());
        userNode.putPOJO("purchasedMovies", user.getPurchasedMovies());
        userNode.putPOJO("watchedMovies", user.getWatchedMovies());
        userNode.putPOJO("likedMovies", user.getLikedMovies());
        userNode.putPOJO("ratedMovies", user.getRatedMovies());
        return userNode;
    }

    public static ObjectNode formatAction(String error, Navigator navigator) {
        ObjectNode actionNode = mapper.createObjectNode();
        actionNode.putPOJO("error", error);
        if (navigator.getCurrentUser() == null) {
            actionNode.putPOJO("currentMoviesList", new ArrayList<>());
        } else {
            actionNode.putPOJO("currentMoviesList", navigator.getCurrentMovies());
        }
        actionNode.putPOJO("currentUser", navigator.getCurrentUser());
        return actionNode;
    }
}
