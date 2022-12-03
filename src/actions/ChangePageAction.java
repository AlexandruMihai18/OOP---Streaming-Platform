package actions;

import database.MoviesDatabase;
import fileio.ActionInput;
import helpers.PageEnum;
import server.Navigator;

import java.util.ArrayList;

public class ChangePageAction extends Action {
    private String page;

    public ChangePageAction(ActionInput action) {
        super(action.getType());
        page = action.getPage();
    }

    @Override
    public void doAction(Navigator navigator) {
        if (!navigator.getCurrentPage().checkNextPage(page)) {
            setOutput("Error", new Navigator());
            return;
        }

        navigator.setCurrentPage(navigator.getCurrentPage().goToNextPage(page));
        navigator.setCurrentMovies(new ArrayList<>());

        if (page.equals(PageEnum.UNAUTHENTICATED_HOMEPAGE)) {
            navigator.setCurrentUser(null);
        }

        if (page.equals(PageEnum.MOVIES_PAGE)) {
            navigator.setCurrentMovies(MoviesDatabase.getInstance().getMovies());
            setOutput(null, navigator);
        }
    }
}
