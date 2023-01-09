package observer;

import database.User;

import java.util.ArrayList;

public interface MyObservable {
    /**
     * Filter potential observers based on certain criteria and adding to the local observers list.
     * In addition, other operation might occur to ensure the synchronization of output
     * @param potentialObservers users that could be observers
     */
    void setObservers(ArrayList<User> potentialObservers);

    /**
     * Notify all observers
     * @param arg notification
     */
    void notifyObservers(Object arg);
}
