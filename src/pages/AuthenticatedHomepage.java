package pages;

import helpers.PageEnum;

public final class AuthenticatedHomepage extends Page {
    public AuthenticatedHomepage() {
        super(PageEnum.AUTHENTICATED_HOMEPAGE);
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
