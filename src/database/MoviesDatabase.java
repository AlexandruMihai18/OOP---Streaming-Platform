package database;

import fileio.MovieInput;

import java.util.ArrayList;

public final class MoviesDatabase {
    private static MoviesDatabase moviesDatabase = null;
    private ArrayList<Movie> movies = new ArrayList<>();

    private MoviesDatabase() {

    }

    public static MoviesDatabase getInstance() {
        if (moviesDatabase == null) {
            moviesDatabase = new MoviesDatabase();
        }
        return moviesDatabase;
    }

    public void resetDatabase() {
        moviesDatabase = null;
    }

    public ArrayList<Movie> getMovies() {
        return movies;
    }

    public void setMovies(final ArrayList<MovieInput> movies) {
        for (MovieInput movie : movies) {
            this.movies.add(new Movie(movie));
        }
    }
}
