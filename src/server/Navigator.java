package server;

import actions.ActionStrategy;
import actions.Recommendation;
import database.ActionsDatabase;
import database.User;
import helpers.Constants;
import pages.Page;
import pages.UnauthenticatedHomepage;

import java.util.ArrayList;

public final class Navigator {
    private Page currentPage = new UnauthenticatedHomepage();
    private ArrayList<Page> allPages = new ArrayList<>();

    public Navigator() {

    }

    public Page getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(final Page currentPage) {
        this.currentPage = currentPage;
    }

    public ArrayList<Page> getAllPages() {
        return allPages;
    }

    public void setAllPages(final ArrayList<Page> allPages) {
        this.allPages = allPages;
    }

    /**
     * Start navigation between pages by following the actions from the actions database
     */
    public void startNavigation() {
        for (ActionStrategy action : ActionsDatabase.getInstance().getActions()) {
            action.actionStrategy(this);
        }

        User currentUser = currentPage.getCurrentUser();

        /**
         * Offering a recommendation for a premium user as a final action
         */
        if (currentUser != null
                && currentUser.getCredentials().getAccountType().equals(Constants.PREMIUM)) {
            ActionStrategy lastAction = new Recommendation();
            lastAction.actionStrategy(this);
            ActionsDatabase.getInstance().getActions().add(lastAction);
        }
    }
}
