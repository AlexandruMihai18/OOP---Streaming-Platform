package helpers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import database.Movie;
import database.User;
import server.Navigator;

import java.util.ArrayList;

public final class FormatOutput {
    private static ObjectMapper mapper = new ObjectMapper();

    private FormatOutput() {

    }

    public static ObjectNode formatMovie(final Movie movie) {
        if (movie == null) {
            return null;
        }

        Movie deepCopyMovie = new Movie(movie);

        ObjectNode movieNode = mapper.createObjectNode();
        movieNode.putPOJO("name", deepCopyMovie.getName());
        movieNode.putPOJO("year", deepCopyMovie.getYear());
        movieNode.putPOJO("duration", deepCopyMovie.getDuration());
        movieNode.putPOJO("genres", deepCopyMovie.getGenres());
        movieNode.putPOJO("actors", deepCopyMovie.getActors());
        movieNode.putPOJO("countriesBanned", deepCopyMovie.getCountriesBanned());
        movieNode.putPOJO("numLikes", deepCopyMovie.getNumLikes());
        movieNode.putPOJO("rating", deepCopyMovie.getRating());
        movieNode.putPOJO("numRatings", deepCopyMovie.getNumRatings());
        return movieNode;
    }

    public static ObjectNode formatUser(final User user) {
        if (user == null) {
            return null;
        }

        User deepCopyUser = new User(user);

        ObjectNode userNode = mapper.createObjectNode();
        userNode.putPOJO("credentials", deepCopyUser.getCredentials());
        userNode.putPOJO("tokensCount", deepCopyUser.getTokensCount());
        userNode.putPOJO("numFreePremiumMovies", deepCopyUser.getNumFreePremiumMovies());
        userNode.putPOJO("purchasedMovies", formatMovieList(deepCopyUser.getPurchasedMovies()));
        userNode.putPOJO("watchedMovies", formatMovieList(deepCopyUser.getWatchedMovies()));
        userNode.putPOJO("likedMovies", formatMovieList(deepCopyUser.getLikedMovies()));
        userNode.putPOJO("ratedMovies", formatMovieList(deepCopyUser.getRatedMovies()));
        return userNode;
    }

    public static ArrayNode formatMovieList(final ArrayList<Movie> movies) {
        ArrayNode moviesNode = mapper.createArrayNode();

        for (Movie movie : movies) {
            moviesNode.add(formatMovie(movie));
        }

        return moviesNode;
    }

    public static ObjectNode formatAction(final String error, final Navigator navigator) {
        ObjectNode actionNode = mapper.createObjectNode();
        actionNode.putPOJO("error", error);
        actionNode.putPOJO("currentMoviesList", formatMovieList(navigator.getCurrentMovies()));
        actionNode.putPOJO("currentUser", formatUser(navigator.getCurrentUser()));
        return actionNode;
    }
}
