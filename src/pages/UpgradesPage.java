package pages;

import helpers.ActionsEnum;
import helpers.PageEnum;

public final class UpgradesPage extends Page {
    public UpgradesPage() {
        super();
    }

    @Override
    public void setNextPages() {
        this.getNextPages().add(PageEnum.AUTHENTICATED_HOMEPAGE);
        this.getNextPages().add(PageEnum.MOVIES_PAGE);
        this.getNextPages().add(PageEnum.UNAUTHENTICATED_HOMEPAGE);
    }

    @Override
    public void setActions() {
        this.getActions().add(ActionsEnum.BUY_TOKENS);
        this.getActions().add(ActionsEnum.BUY_PREMIUM_ACCOUNT);
    }
}
