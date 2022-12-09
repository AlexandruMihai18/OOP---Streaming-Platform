package pages;

import helpers.PageEnum;

import java.util.ArrayList;

public abstract class Page {
    private ArrayList<String> nextPages;
    private ArrayList<String> actions;

    public Page() {
        nextPages = new ArrayList<>();
        actions = new ArrayList<>();
        setNextPages();
        setActions();
    }

    public ArrayList<String> getNextPages() {
        return nextPages;
    }

    public abstract void setNextPages();

    public ArrayList<String> getActions() {
        return actions;
    }

    public abstract void setActions();

    public boolean checkNextPage(final String nextPage) {
        return nextPages.contains(nextPage);
    }

    public boolean checkAction(final String action) {
        return actions.contains(action);
    }

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
