package actions;

import database.Credentials;
import database.User;
import database.UsersDatabase;
import fileio.ActionInput;
import pages.AuthenticatedHomepage;
import pages.UnauthenticatedHomepage;
import server.Navigator;

public final class OnPageLogin extends Action {
    private String feature;
    private Credentials credentials;

    public OnPageLogin(final ActionInput action) {
        super(action.getType());
        feature = action.getFeature();
        credentials = new Credentials(action.getCredentials());
    }

    @Override
    public void actionStrategy(final Navigator navigator) {
        /**
         * Check the features for this page
         */
        if (!navigator.getCurrentPage().checkAction(feature)) {
            setError();
            return;
        }

        /**
         * Check if there is a user with those credentials
         */
        User newUser = getUser();
        if (newUser == null) {
            setError();
            navigator.setCurrentPage(new UnauthenticatedHomepage());
            return;
        }

        /**
         * Log in action - mark the currentUser
         */
        navigator.setCurrentPage(new AuthenticatedHomepage());
        navigator.getCurrentPage().setCurrentUser(newUser);
        setOutput(navigator);
    }

    /**
     * Find the User inside the users Database
     * @return the found user / null otherwise
     */
    public User getUser() {
        for (User user : UsersDatabase.getInstance().getUsers()) {
            if (user.getCredentials().getName().equals(credentials.getName())) {
                if (user.getCredentials().getPassword().equals(credentials.getPassword())) {
                    return user;
                }
            }
        }
        return null;
    }
}
