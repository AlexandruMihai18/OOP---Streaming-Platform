package database;

import fileio.UserInput;

import java.util.ArrayList;

/**
 * Singleton class used to store the users
 */
public final class UsersDatabase {
    private static UsersDatabase usersDatabase = null;
    private ArrayList<User> users = new ArrayList<>();

    private UsersDatabase() {

    }

    /**
     * Access the singleton class
     * @return the users class instance
     */
    public static UsersDatabase getInstance() {
        if (usersDatabase == null) {
            usersDatabase = new UsersDatabase();
        }
        return usersDatabase;
    }

    /**
     * Mark the single instance as null
     */
    public void resetDatabase() {
        usersDatabase = null;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    /**
     * Add the user to the users database
     * @param users given input users
     */
    public void setUsers(final ArrayList<UserInput> users) {
        for (UserInput user : users) {
            this.users.add(new User(user));
        }
    }
}
