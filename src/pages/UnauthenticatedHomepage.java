package pages;

import helpers.PageEnum;

public class UnauthenticatedHomepage extends Page{
    public UnauthenticatedHomepage() {
        super();
    }

    @Override
    public void setNextPages() {
        this.getNextPages().add(PageEnum.LOGIN_PAGE);
        this.getNextPages().add(PageEnum.REGISTER_PAGE);
    }

    @Override
    public void setActions() {

    }
}
