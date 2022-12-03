package actions;

import com.fasterxml.jackson.databind.node.ObjectNode;
import helpers.FormatOutput;
import server.Navigator;

public abstract class Action {
    private String type;
    ObjectNode outputNode = null;

    public Action(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ObjectNode getOutputNode() {
        return outputNode;
    }

    public void setOutputNode(ObjectNode outputNode) {
        this.outputNode = outputNode;
    }

    public abstract void doAction(Navigator navigator);

    public void setOutput(String error, Navigator navigator) {
        outputNode = FormatOutput.formatAction(error, navigator);
    }
}
