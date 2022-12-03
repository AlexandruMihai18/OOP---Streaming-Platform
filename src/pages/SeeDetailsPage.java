package pages;

import helpers.ActionsEnum;
import helpers.PageEnum;

public class SeeDetailsPage extends Page {
    public SeeDetailsPage() {
        super();
    }

    @Override
    public void setNextPages() {
        this.getNextPages().add(PageEnum.AUTHENTICATED_HOMEPAGE);
        this.getNextPages().add(PageEnum.MOVIES_PAGE);
        this.getNextPages().add(PageEnum.UPGRADES_PAGE);
        this.getNextPages().add(PageEnum.UNAUTHENTICATED_HOMEPAGE);
    }

    @Override
    public void setActions() {
        this.getActions().add(ActionsEnum.PURCHASE);
        this.getActions().add(ActionsEnum.WATCH);
        this.getActions().add(ActionsEnum.LIKE);
        this.getActions().add(ActionsEnum.RATE_THE_MOVIE);
    }
}
