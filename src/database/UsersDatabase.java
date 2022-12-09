package database;

import fileio.UserInput;

import java.util.ArrayList;

public final class UsersDatabase {
    private static UsersDatabase usersDatabase = null;
    private ArrayList<User> users = new ArrayList<>();

    private UsersDatabase() {

    }

    public static UsersDatabase getInstance() {
        if (usersDatabase == null) {
            usersDatabase = new UsersDatabase();
        }
        return usersDatabase;
    }

    public void resetDatabase() {
        usersDatabase = null;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(final ArrayList<UserInput> users) {
        for (UserInput user : users) {
            this.users.add(new User(user));
        }
    }
}
