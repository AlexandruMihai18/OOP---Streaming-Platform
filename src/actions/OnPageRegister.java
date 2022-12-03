package actions;

import database.Credentials;
import database.User;
import database.UsersDatabase;
import fileio.ActionInput;
import pages.AuthenticatedHomepage;
import pages.UnauthenticatedHomepage;
import server.Navigator;

public class OnPageRegister extends Action {
    private String page;
    private String feature;
    private Credentials credentials;

    public OnPageRegister(ActionInput action) {
        super(action.getType());
        page = action.getPage();
        feature = action.getFeature();
        credentials = new Credentials(action.getCredentials());
    }

    @Override
    public void doAction(Navigator navigator) {
        if (!navigator.getCurrentPage().checkAction(feature)) {
            setOutput("Error", new Navigator());
            return;
        }

        User checkUser = getUser();

        if (checkUser != null) {
            setOutput("Error", new Navigator());
            navigator.setCurrentPage(new UnauthenticatedHomepage());
            return;
        }

        User user = new User(credentials);
        UsersDatabase.getInstance().getUsers().add(user);
        navigator.setCurrentPage(new AuthenticatedHomepage());
        navigator.setCurrentUser(user);
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
