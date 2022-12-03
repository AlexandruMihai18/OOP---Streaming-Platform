package actions;

import fileio.ActionInput;
import server.Navigator;

public class ChangePageMovieAction extends Action {
    private String page;
    private String movie;

    public ChangePageMovieAction(ActionInput action) {
        super(action.getType());
        page = action.getPage();
        movie = action.getMovie();
    }

    @Override
    public void doAction(Navigator navigator) {

    }
}
