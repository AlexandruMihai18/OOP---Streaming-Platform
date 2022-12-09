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
    public void doAction(final Navigator navigator) {
        if (!navigator.getCurrentPage().checkAction(feature)) {
            setOutput("Error", new Navigator());
            return;
        }

        User newUser = getUser();
        if (newUser == null) {
            setOutput("Error", new Navigator());
            navigator.setCurrentPage(new UnauthenticatedHomepage());
            return;
        }

        navigator.setCurrentUser(newUser);
        navigator.setCurrentPage(new AuthenticatedHomepage());
        setOutput(null, navigator);
    }

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
