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

    /**
     * Format the movie output using the relevant fields
     * @param movie given movie to display
     * @return ObjectNode containing the relevant movie information
     */
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

    /**
     * Format the user output using the relevant fields
     * @param user given user to display
     * @return ObjectNode containing the relevant user information
     */
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

    /**
     * Format a list of movies
     * @param movies given list of movies
     * @return ArrayNode containing the relevant information about movies
     */
    public static ArrayNode formatMovieList(final ArrayList<Movie> movies) {
        ArrayNode moviesNode = mapper.createArrayNode();

        for (Movie movie : movies) {
            moviesNode.add(formatMovie(movie));
        }

        return moviesNode;
    }

    /**
     * Format the action output using the relevant field
     * @param error error message / null
     * @param navigator currentUser, currentMovies
     * @return ObjectNode containing the output message, as well as the currentUser
     *         and current Movies displayed
     */
    public static ObjectNode formatAction(final String error, final Navigator navigator) {
        ObjectNode actionNode = mapper.createObjectNode();
        actionNode.putPOJO("error", error);
        actionNode.putPOJO("currentMoviesList", formatMovieList(navigator.getCurrentMovies()));
        actionNode.putPOJO("currentUser", formatUser(navigator.getCurrentUser()));
        return actionNode;
    }
}
