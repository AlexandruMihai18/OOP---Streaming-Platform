package pages;

import helpers.ActionFactory;
import helpers.PageEnum;

public final class MoviePage extends Page {
    public MoviePage() {
        super();
    }

    @Override
    public void setNextPages() {
        this.getNextPages().add(PageEnum.AUTHENTICATED_HOMEPAGE);
        this.getNextPages().add(PageEnum.SEE_DETAILS_PAGE);
        this.getNextPages().add(PageEnum.UNAUTHENTICATED_HOMEPAGE);
        this.getNextPages().add(PageEnum.MOVIES_PAGE);
    }

    @Override
    public void setActions() {
        this.getActions().add(ActionFactory.SEARCH);
        this.getActions().add(ActionFactory.FILTERS);
    }
}
