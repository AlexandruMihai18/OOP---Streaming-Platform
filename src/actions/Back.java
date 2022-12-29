package actions;

import fileio.ActionInput;
import pages.Page;
import server.Navigator;

public final class Back extends ActionStrategy {
    public Back(final ActionInput action) {
        super(action.getType());
    }

    @Override
    public void actionStrategy(final Navigator navigator) {
        /**
         * Checking if a user is logged
         */
        if (navigator.getCurrentPage().getCurrentUser() == null) {
            setError();
            return;
        }

        /**
         * Checking if there is a page that we can return to
         */
        if (navigator.getAllPages().isEmpty()) {
            setError();
            return;
        }

        /**
         * Moving to the previous page
         */
        Page previousPage = navigator.getAllPages().remove(navigator.getAllPages().size() - 1);

        navigator.setCurrentPage(previousPage);

        if (!navigator.getCurrentPage().getCurrentMovies().isEmpty()) {
            setOutput(navigator);
        }
    }
}
