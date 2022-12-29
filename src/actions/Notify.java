package actions;

import database.Notification;
import database.User;

import java.util.ArrayList;

public interface Notify {
    /**
     * Notifying the users regarding a change in the movie Database or recommendation
     * @param users list of users that need to be notified
     * @param notification notification message that needs to be transferred
     */
    void notifyUsers(ArrayList<User> users, Notification notification);
}
