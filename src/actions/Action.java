package actions;

import com.fasterxml.jackson.databind.node.ObjectNode;
import helpers.Constants;
import helpers.FormatOutput;
import server.Navigator;

/**
 * Strategy Pattern class designed to identify each type of possible action.
 * The doAction method is defined as abstract and implemented accordingly to
 * each algorithm needs in order to change the navigator.
 */
public abstract class Action {
    private String type;
    private ObjectNode outputNode = null;

    public Action(final String type) {
        this.type = type;
    }

    /**
     * Type corresponding to the sub-action
     * @return change page / on page
     */
    public String getType() {
        return type;
    }

    /**
     * Inherit the type from the child class
     * @param type change page / on page
     */
    public void setType(final String type) {
        this.type = type;
    }

    /**
     * Retrieve the information from the action
     * @return OutputNode that contains the action output
     */
    public ObjectNode getOutputNode() {
        return outputNode;
    }

    /**
     * Changes applied to the server -- key implementation for the Strategy Design Pattern
     * @param navigator currentPage, currentUser, currentMovies required for action
     */
    public abstract void doAction(Navigator navigator);

    /**
     * Displaying the output for a successful action
     * @param navigator information regarding the currentUser and currentMovies
     */
    public void setOutput(final Navigator navigator) {
        outputNode = FormatOutput.formatAction(null, navigator);
    }

    /**
     * Displaying a generic error message for a faulty action
     */
    public void setError() {
        outputNode = FormatOutput.formatAction(Constants.ERROR, new Navigator());
    }

}
