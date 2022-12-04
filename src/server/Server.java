package server;

import actions.Action;
import com.fasterxml.jackson.databind.node.ArrayNode;
import database.ActionsDatabase;
import database.MoviesDatabase;
import database.UsersDatabase;
import fileio.Input;

public final class Server {
    private static Server server;
    private Input input;
    private Navigator navigator = new Navigator();

    public static Server getServer() {
        if (server == null) {
            server = new Server();
        }
        return server;
    }

    public void resetServer() {
        server = null;
    }

    public void uploadData(final Input inputData) {
        input = inputData;
    }

    public void startServer() {
        UsersDatabase.getInstance().setUsers(input.getUsers());
        MoviesDatabase.getInstance().setMovies(input.getMovies());
        ActionsDatabase.getInstance().setActions(input.getActions());
        navigator.startNavigation();
    }

    public void importOutput(final ArrayNode output) {
        for (Action action : ActionsDatabase.getInstance().getActions()) {
            if (action.getOutputNode() != null) {
                output.add(action.getOutputNode());
            }
        }
    }
}
