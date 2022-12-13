package database;

import actions.Action;
import fileio.ActionInput;
import helpers.ActionFactory;

import java.util.ArrayList;

/**
 * Singleton class used to store the actions
 */
public final class ActionsDatabase {
    private static ActionsDatabase actionsDatabase = null;
    private ArrayList<Action> actions = new ArrayList<>();

    private ActionsDatabase() {

    }

    /**
     * Access the singleton class
     * @return the actions class instance
     */
    public static ActionsDatabase getInstance() {
        if (actionsDatabase == null) {
            actionsDatabase = new ActionsDatabase();
        }
        return actionsDatabase;
    }

    /**
     * Mark the single instance as null
     */
    public void resetDatabase() {
        actionsDatabase = null;
    }

    public ArrayList<Action> getActions() {
        return actions;
    }

    /**
     * Using a Factory class to specify each type of action
     * @param actions given input actions
     */
    public void setActions(final ArrayList<ActionInput> actions) {
        for (ActionInput action : actions) {
            this.actions.add(ActionFactory.createAction(action));
        }
    }
}
