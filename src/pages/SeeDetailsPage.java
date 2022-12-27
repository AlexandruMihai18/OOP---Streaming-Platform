package pages;

import helpers.ActionFactory;
import helpers.PageEnum;

public final class SeeDetailsPage extends Page {

    public SeeDetailsPage() {
        super(PageEnum.SEE_DETAILS_PAGE);
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
        this.getActions().add(ActionFactory.PURCHASE);
        this.getActions().add(ActionFactory.WATCH);
        this.getActions().add(ActionFactory.LIKE);
        this.getActions().add(ActionFactory.RATE_THE_MOVIE);
        this.getActions().add(ActionFactory.SUBSCRIBE);
    }
}
