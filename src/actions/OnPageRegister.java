package actions;

import database.Credentials;
import database.User;
import database.UsersDatabase;
import fileio.ActionInput;
import pages.AuthenticatedHomepage;
import pages.UnauthenticatedHomepage;
import server.Navigator;

public final class OnPageRegister extends Action {
    private String feature;
    private Credentials credentials;

    public OnPageRegister(final ActionInput action) {
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

        User checkUser = getUser();

        /**
         * Check if the user is already in the database
         */
        if (checkUser != null) {
            setError();
            navigator.setCurrentPage(new UnauthenticatedHomepage());
            return;
        }

        /**
         * Register the user and add it to the database
         */
        User user = new User(credentials);
        UsersDatabase.getInstance().getUsers().add(user);
        navigator.setCurrentPage(new AuthenticatedHomepage());
        navigator.getCurrentPage().setCurrentUser(user);
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
