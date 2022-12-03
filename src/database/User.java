package database;

import fileio.UserInput;
import helpers.MagicNumbers;

import java.util.ArrayList;

public final class User {
    private Credentials credentials;
    private int tokensCount;
    private int numFreePremiumMovies;
    private ArrayList<Movie> purchasedMovies;
    private ArrayList<Movie> watchedMovies;
    private ArrayList<Movie> likedMovies;
    private ArrayList<Movie> ratedMovies;

    public User(Credentials credentials) {
        this.credentials = credentials;
        tokensCount = 0;
        numFreePremiumMovies = MagicNumbers.NO_PREMIUM_MOVIES;
        purchasedMovies = new ArrayList<>();
        watchedMovies = new ArrayList<>();
        likedMovies = new ArrayList<>();
        ratedMovies = new ArrayList<>();
    }
    public User(UserInput user) {
        credentials = new Credentials(user.getCredentials());
        tokensCount = 0;
        numFreePremiumMovies = MagicNumbers.NO_PREMIUM_MOVIES;
        purchasedMovies = new ArrayList<>();
        watchedMovies = new ArrayList<>();
        likedMovies = new ArrayList<>();
        ratedMovies = new ArrayList<>();
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }

    public int getTokensCount() {
        return tokensCount;
    }

    public void setTokensCount(int tokensCount) {
        this.tokensCount = tokensCount;
    }

    public int getNumFreePremiumMovies() {
        return numFreePremiumMovies;
    }

    public void setNumFreePremiumMovies(int numFreePremiumMovies) {
        this.numFreePremiumMovies = numFreePremiumMovies;
    }

    public ArrayList<Movie> getPurchasedMovies() {
        return purchasedMovies;
    }

    public void setPurchasedMovies(ArrayList<Movie> purchasedMovies) {
        this.purchasedMovies = purchasedMovies;
    }

    public ArrayList<Movie> getWatchedMovies() {
        return watchedMovies;
    }

    public void setWatchedMovies(ArrayList<Movie> watchedMovies) {
        this.watchedMovies = watchedMovies;
    }

    public ArrayList<Movie> getLikedMovies() {
        return likedMovies;
    }

    public void setLikedMovies(ArrayList<Movie> likedMovies) {
        this.likedMovies = likedMovies;
    }

    public ArrayList<Movie> getRatedMovies() {
        return ratedMovies;
    }

    public void setRatedMovies(ArrayList<Movie> ratedMovies) {
        this.ratedMovies = ratedMovies;
    }

    @Override
    public String toString() {
        return "User{" +
                "credentials=" + credentials +
                ", tokensCount=" + tokensCount +
                ", numFreePremiumMovies=" + numFreePremiumMovies +
                ", purchasedMovies=" + purchasedMovies +
                ", watchedMovies=" + watchedMovies +
                ", likedMovies=" + likedMovies +
                ", ratedMovies=" + ratedMovies +
                '}';
    }
}
