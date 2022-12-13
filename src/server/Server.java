package server;

import actions.Action;
import com.fasterxml.jackson.databind.node.ArrayNode;
import database.ActionsDatabase;
import database.MoviesDatabase;
import database.UsersDatabase;
import fileio.Input;

/**
 * Singleton class used for the server implementation
 */
public final class Server {
    private static Server server;
    private Input input;
    private Navigator navigator = new Navigator();

    /**
     * Access the singleton class
     * @return the server class instance
     */
    public static Server getServer() {
        if (server == null) {
            server = new Server();
        }
        return server;
    }

    /**
     * Mark the server instance as null
     */
    public void resetServer() {
        server = null;
    }

    /**
     * Upload the data inside the server
     * @param inputData JSON formatted information receive from the input files
     */
    public void uploadData(final Input inputData) {
        input = inputData;
    }

    /**
     * Start the server by resetting the databases and upload the new users, movie
     * and actions. In addition, start the navigation process using based on actions.
     */
    public void startServer() {
        UsersDatabase.getInstance().resetDatabase();
        MoviesDatabase.getInstance().resetDatabase();
        ActionsDatabase.getInstance().resetDatabase();

        UsersDatabase.getInstance().setUsers(input.getUsers());
        MoviesDatabase.getInstance().setMovies(input.getMovies());
        ActionsDatabase.getInstance().setActions(input.getActions());

        navigator.startNavigation();
    }

    /**
     * Import the output from each action that sends an output, formatted as a JSON node.
     * @param output ArrayNode used to store the output information, used to display it in main
     */
    public void importOutput(final ArrayNode output) {
        for (Action action : ActionsDatabase.getInstance().getActions()) {
            if (action.getOutputNode() != null) {
                output.add(action.getOutputNode());
            }
        }
    }
}
