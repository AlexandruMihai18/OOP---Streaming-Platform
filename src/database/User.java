package database;

import fileio.UserInput;
import helpers.Constants;

import java.util.ArrayList;

public final class User {
    private Credentials credentials;
    private int tokensCount;
    private int numFreePremiumMovies;
    private ArrayList<Movie> purchasedMovies;
    private ArrayList<Movie> watchedMovies;
    private ArrayList<Movie> likedMovies;
    private ArrayList<Movie> ratedMovies;
    private ArrayList<Movie> asynchronousMovies;
    private ArrayList<String> subscribedGenres;
    private ArrayList<Notification> notifications;

    public User(final User user) {
        this.credentials = new Credentials(user.getCredentials());
        this.tokensCount = user.getTokensCount();
        this.numFreePremiumMovies = user.getNumFreePremiumMovies();
        this.purchasedMovies = new ArrayList<>(user.getPurchasedMovies());
        this.watchedMovies = new ArrayList<>(user.getWatchedMovies());
        this.likedMovies = new ArrayList<>(user.getLikedMovies());
        this.ratedMovies = new ArrayList<>(user.getRatedMovies());
        this.asynchronousMovies = new ArrayList<>(user.getAsynchronousMovies());
        this.subscribedGenres = new ArrayList<>(user.getSubscribedGenres());
        this.notifications = new ArrayList<>(user.getNotifications());
    }

    public User(final Credentials credentials) {
        this.credentials = credentials;
        tokensCount = 0;
        numFreePremiumMovies = Constants.NO_PREMIUM_MOVIES;
        purchasedMovies = new ArrayList<>();
        watchedMovies = new ArrayList<>();
        likedMovies = new ArrayList<>();
        ratedMovies = new ArrayList<>();
        asynchronousMovies = new ArrayList<>();
        subscribedGenres = new ArrayList<>();
        notifications = new ArrayList<>();
    }
    public User(final UserInput user) {
        credentials = new Credentials(user.getCredentials());
        tokensCount = 0;
        numFreePremiumMovies = Constants.NO_PREMIUM_MOVIES;
        purchasedMovies = new ArrayList<>();
        watchedMovies = new ArrayList<>();
        likedMovies = new ArrayList<>();
        ratedMovies = new ArrayList<>();
        asynchronousMovies = new ArrayList<>();
        subscribedGenres = new ArrayList<>();
        notifications = new ArrayList<>();
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(final Credentials credentials) {
        this.credentials = credentials;
    }

    public int getTokensCount() {
        return tokensCount;
    }

    public void setTokensCount(final int tokensCount) {
        this.tokensCount = tokensCount;
    }

    public int getNumFreePremiumMovies() {
        return numFreePremiumMovies;
    }

    public void setNumFreePremiumMovies(final int numFreePremiumMovies) {
        this.numFreePremiumMovies = numFreePremiumMovies;
    }

    public ArrayList<Movie> getPurchasedMovies() {
        return purchasedMovies;
    }

    public void setPurchasedMovies(final ArrayList<Movie> purchasedMovies) {
        this.purchasedMovies = purchasedMovies;
    }

    public ArrayList<Movie> getWatchedMovies() {
        return watchedMovies;
    }

    public void setWatchedMovies(final ArrayList<Movie> watchedMovies) {
        this.watchedMovies = watchedMovies;
    }

    public ArrayList<Movie> getLikedMovies() {
        return likedMovies;
    }

    public void setLikedMovies(final ArrayList<Movie> likedMovies) {
        this.likedMovies = likedMovies;
    }

    public ArrayList<Movie> getRatedMovies() {
        return ratedMovies;
    }

    public void setRatedMovies(final ArrayList<Movie> ratedMovies) {
        this.ratedMovies = ratedMovies;
    }

    public ArrayList<Movie> getAsynchronousMovies() {
        return asynchronousMovies;
    }

    public void setAsynchronousMovies(final ArrayList<Movie> asynchronousMovies) {
        this.asynchronousMovies = asynchronousMovies;
    }

    public ArrayList<String> getSubscribedGenres() {
        return subscribedGenres;
    }

    public void setSubscribedGenres(final ArrayList<String> subscribedGenres) {
        this.subscribedGenres = subscribedGenres;
    }

    public ArrayList<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(final ArrayList<Notification> notifications) {
        this.notifications = notifications;
    }
}
