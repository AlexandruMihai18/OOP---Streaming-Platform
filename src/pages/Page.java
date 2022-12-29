package pages;

import database.Movie;
import database.User;
import helpers.PageEnum;

import java.util.ArrayList;

public abstract class Page {
    private String pageName;
    private ArrayList<String> nextPages;
    private ArrayList<String> actions;
    private User currentUser;
    private ArrayList<Movie> currentMovies;
    private ArrayList<Movie> allMoviesFromPage;
    private String movieName;

    public Page(final String pageName) {
        this.pageName = pageName;
        nextPages = new ArrayList<>();
        actions = new ArrayList<>();
        currentUser = null;
        currentMovies = new ArrayList<>();
        allMoviesFromPage = new ArrayList<>();
        setNextPages();
        setActions();
    }

    public Page(final Page page) {
        nextPages = page.getNextPages();
        actions = page.getActions();
        currentUser = page.getCurrentUser();
        currentMovies = page.getCurrentMovies();
        allMoviesFromPage = page.getAllMoviesFromPage();
    }

    /**
     * Get the page name
     * @return page name
     */
    public String getPageName() {
        return pageName;
    }

    /**
     * Set the page name
     * @param pageName given name of the page
     */
    public void setPageName(final String pageName) {
        this.pageName = pageName;
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

    /**
     * Get the user on the page
     * @return user on the page
     */
    public User getCurrentUser() {
        return currentUser;
    }

    /**
     * Set the user for the page
     * @param currentUser user that accesses the page
     */
    public void setCurrentUser(final User currentUser) {
        this.currentUser = currentUser;
    }

    /**
     * Get the movies displayed on the page
     * @return movies displayed on the page
     */
    public ArrayList<Movie> getCurrentMovies() {
        return currentMovies;
    }

    /**
     * Set the movies visible on the page
     * @param currentMovies movies visible on the page
     */
    public void setCurrentMovies(final ArrayList<Movie> currentMovies) {
        this.currentMovies = currentMovies;
    }

    /**
     * Get the movies available on the page
     * @return movies available on the page
     */
    public ArrayList<Movie> getAllMoviesFromPage() {
        return allMoviesFromPage;
    }

    /**
     * Set the movies available on the page
     * @param allMoviesFromPage movies available on the page
     */
    public void setAllMoviesFromPage(final ArrayList<Movie> allMoviesFromPage) {
        this.allMoviesFromPage = allMoviesFromPage;
    }

    /**
     * Get the movie name on the page (designed for the SeeDetails page)
     * @return name of the movie on the page
     */
    public String getMovieName() {
        return movieName;
    }

    /**
     * Set the movie name on the page (designed for the SeeDetails page)
     * @param movieName name of the movie on the page
     */
    public void setMovieName(final String movieName) {
        this.movieName = movieName;
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
