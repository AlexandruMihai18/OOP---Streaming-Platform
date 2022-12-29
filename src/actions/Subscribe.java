package actions;

import database.User;
import fileio.ActionInput;
import server.Navigator;

public final class Subscribe extends ActionStrategy {
    private String subscribedGenre;

    public Subscribe(final ActionInput action) {
        super(action.getType());
        subscribedGenre = action.getSubscribedGenre();
    }

    @Override
    public void actionStrategy(final Navigator navigator) {
        if (!navigator.getCurrentPage().checkAction(getType())) {
            setError();
            return;
        }

        User currentUser = navigator.getCurrentPage().getCurrentUser();

        /**
         * Checking if a user is already subscribed to a genre
         */
        if (currentUser.getSubscribedGenres().contains(subscribedGenre)) {
            setError();
            return;
        }

        currentUser.getSubscribedGenres().add(subscribedGenre);
    }
}
