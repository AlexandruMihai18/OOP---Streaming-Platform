package actions;

import com.fasterxml.jackson.databind.node.ObjectNode;
import helpers.FormatOutput;
import server.Navigator;

public abstract class Action {
    private String type;
    private ObjectNode outputNode = null;

    public Action(final String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public ObjectNode getOutputNode() {
        return outputNode;
    }

    public void setOutputNode(final ObjectNode outputNode) {
        this.outputNode = outputNode;
    }

    public abstract void doAction(Navigator navigator);

    public void setOutput(final String error, final Navigator navigator) {
        outputNode = FormatOutput.formatAction(error, navigator);
    }
}
