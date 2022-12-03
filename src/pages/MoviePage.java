package pages;

import helpers.ActionsEnum;
import helpers.PageEnum;

public class MoviePage extends Page{
    public MoviePage() {
        super();
    }

    @Override
    public void setNextPages() {
        this.getNextPages().add(PageEnum.AUTHENTICATED_HOMEPAGE);
        this.getNextPages().add(PageEnum.SEE_DETAILS_PAGE);
        this.getNextPages().add(PageEnum.UNAUTHENTICATED_HOMEPAGE);
    }

    @Override
    public void setActions() {
        this.getActions().add(ActionsEnum.SEARCH);
        this.getActions().add(ActionsEnum.FILTERS);
    }
}
