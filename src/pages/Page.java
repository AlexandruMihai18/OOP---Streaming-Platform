package pages;

import database.Movie;
import database.User;
import helpers.PageEnum;

import java.util.ArrayList;

public abstract class Page {
    private ArrayList<String> nextPages;
    private ArrayList<String> actions;
    private User currentUser;
    private ArrayList<Movie> currentMovies;
    private ArrayList<Movie> allMoviesFromPage;

    public Page() {
        nextPages = new ArrayList<>();
        actions = new ArrayList<>();
        currentUser = null;
        currentMovies = new ArrayList<>();
        allMoviesFromPage = new ArrayList<>();
        setNextPages();
        setActions();
    }

    public Page(Page page) {
        nextPages = page.getNextPages();
        actions = page.getActions();
        currentUser = page.getCurrentUser();
        currentMovies = page.getCurrentMovies();
        allMoviesFromPage = page.getAllMoviesFromPage();
    }

    /**
     * Get the following pages from the current one
     * @return next pages available
     */
    public ArrayList<String> getNextPages() {
        return nextPages;
    }

    /**
     * Set next pages available based on the current page name
     */
    public abstract void setNextPages();

    /**
     * Get the current actions that can be implemented on the page
     * @return actions available on page
     */
    public ArrayList<String> getActions() {
        return actions;
    }

    /**
     * Set actions available on page based on the current page name
     */
    public abstract void setActions();

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(final User currentUser) {
        this.currentUser = currentUser;
    }

    public ArrayList<Movie> getCurrentMovies() {
        return currentMovies;
    }

    public void setCurrentMovies(final ArrayList<Movie> currentMovies) {
        this.currentMovies = currentMovies;
    }

    public ArrayList<Movie> getAllMoviesFromPage() {
        return allMoviesFromPage;
    }

    public void setAllMoviesFromPage(final ArrayList<Movie> allMoviesFromPage) {
        this.allMoviesFromPage = allMoviesFromPage;
    }

    /**
     * Check if a page follows the current one
     * @param nextPage the inquired page
     * @return true - the next page can be reach from the current one, false - otherwise
     */
    public boolean checkNextPage(final String nextPage) {
        return nextPages.contains(nextPage);
    }

    /**
     * Check if an action can be applied on the current page
     * @param action the inquired action
     * @return true - the action can be applied on the current page, false - otherwise
     */
    public boolean checkAction(final String action) {
        return actions.contains(action);
    }

    /**
     * Page changes flow - the user can browse from one page to another
     * @param nextPage next page name
     * @return the next page based on name
     */
    public Page goToNextPage(final String nextPage) {
        switch (nextPage) {
            case PageEnum.UNAUTHENTICATED_HOMEPAGE -> {
                return new UnauthenticatedHomepage();
            }
            case PageEnum.LOGIN_PAGE -> {
                return new LoginPage();
            }
            case PageEnum.REGISTER_PAGE -> {
                return new RegisterPage();
            }
            case PageEnum.MOVIES_PAGE -> {
                return new MoviePage();
            }
            case PageEnum.UPGRADES_PAGE -> {
                return new UpgradesPage();
            }
            case PageEnum.SEE_DETAILS_PAGE -> {
                return new SeeDetailsPage();
            }
            case PageEnum.AUTHENTICATED_HOMEPAGE -> {
                return new AuthenticatedHomepage();
            }
            default -> {
                return null;
            }
        }
    }
}
