package actions;

import database.User;
import fileio.ActionInput;
import server.Navigator;

public final class OnPageSubscribe extends ActionStrategy {
    private String feature;
    private String subscribedGenre;

    public OnPageSubscribe(final ActionInput action) {
        super(action.getType());
        feature = action.getFeature();
        subscribedGenre = action.getSubscribedGenre();
    }

    @Override
    public void actionStrategy(final Navigator navigator) {
        if (!navigator.getCurrentPage().checkAction(feature)) {
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
