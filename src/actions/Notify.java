package actions;

import database.Notification;
import database.User;

import java.util.ArrayList;

public interface Notify {
    void notifyUsers(ArrayList<User> users, Notification notification);
}
