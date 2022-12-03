package server;

import actions.Action;
import database.ActionsDatabase;
import database.Movie;
import database.User;
import pages.Page;
import pages.UnauthenticatedHomepage;

import java.util.ArrayList;

public final class Navigator {
    private Page currentPage = new UnauthenticatedHomepage();
    private ArrayList<Movie> currentMovies = new ArrayList<>();
    private User currentUser = null;

    public Navigator() {

    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public Page getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Page currentPage) {
        this.currentPage = currentPage;
    }

    public ArrayList<Movie> getCurrentMovies() {
        return currentMovies;
    }

    public void setCurrentMovies(ArrayList<Movie> currentMovies) {
        this.currentMovies = currentMovies;
    }

    public void startNavigation() {
        for (Action action : ActionsDatabase.getInstance().getActions()) {
            action.doAction(this);
        }
    }
}
