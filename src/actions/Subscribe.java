package actions;

import fileio.ActionInput;
import server.Navigator;

public final class Subscribe extends Action {
    private String subscribedGenre;

    public Subscribe(final ActionInput action) {
        super(action.getType());
        subscribedGenre = action.getSubscribedGenre();
    }

    @Override
    public void actionStrategy(Navigator navigator) {
        if (!navigator.getCurrentPage().checkAction(getType())) {
            setError();
            return;
        }

        if (navigator.getCurrentPage().getCurrentUser().getSubscribedGenres().contains(subscribedGenre)) {
            setError();
            return;
        }

        navigator.getCurrentPage().getCurrentUser().getSubscribedGenres().add(subscribedGenre);
    }
}
