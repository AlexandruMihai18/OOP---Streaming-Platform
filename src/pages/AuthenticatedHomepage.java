package pages;

import helpers.PageEnum;

public class AuthenticatedHomepage extends Page {
    public AuthenticatedHomepage() {
        super();
    }

    @Override
    public void setNextPages() {
        this.getNextPages().add(PageEnum.MOVIES_PAGE);
        this.getNextPages().add(PageEnum.UPGRADES_PAGE);
        this.getNextPages().add(PageEnum.UNAUTHENTICATED_HOMEPAGE);
    }

    @Override
    public void setActions() {

    }
}
