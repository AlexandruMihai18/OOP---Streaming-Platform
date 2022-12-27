package actions;

import fileio.ActionInput;
import server.Navigator;

public final class Back extends Action {
    public Back(final ActionInput action) {
        super(action.getType());
    }

    @Override
    public void actionStrategy(final Navigator navigator) {

    }
}
