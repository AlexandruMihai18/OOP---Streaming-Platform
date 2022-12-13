package database;

import fileio.MovieInput;

import java.util.ArrayList;

/**
 * Singleton class used to store the movies
 */
public final class MoviesDatabase {
    private static MoviesDatabase moviesDatabase = null;
    private ArrayList<Movie> movies = new ArrayList<>();

    private MoviesDatabase() {

    }

    /**
     * Access the singleton class
     * @return the movies class instance
     */
    public static MoviesDatabase getInstance() {
        if (moviesDatabase == null) {
            moviesDatabase = new MoviesDatabase();
        }
        return moviesDatabase;
    }

    /**
     * Mark the single instance as null
     */
    public void resetDatabase() {
        moviesDatabase = null;
    }

    public ArrayList<Movie> getMovies() {
        return movies;
    }

    /**
     * Add the movie to the movies database
     * @param movies given input movies
     */
    public void setMovies(final ArrayList<MovieInput> movies) {
        for (MovieInput movie : movies) {
            this.movies.add(new Movie(movie));
        }
    }
}
