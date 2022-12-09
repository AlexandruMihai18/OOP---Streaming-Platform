package database;

import actions.Action;
import fileio.ActionInput;
import helpers.ActionBuilder;

import java.util.ArrayList;

public final class ActionsDatabase {
    private static ActionsDatabase actionsDatabase = null;
    private ArrayList<Action> actions = new ArrayList<>();

    private ActionsDatabase() {

    }

    public static ActionsDatabase getInstance() {
        if (actionsDatabase == null) {
            actionsDatabase = new ActionsDatabase();
        }
        return actionsDatabase;
    }

    public void resetDatabase() {
        actionsDatabase = null;
    }

    public ArrayList<Action> getActions() {
        return actions;
    }

    public void setActions(final ArrayList<ActionInput> actions) {
        for (ActionInput action : actions) {
            this.actions.add(ActionBuilder.buildAction(action));
        }
    }
}
