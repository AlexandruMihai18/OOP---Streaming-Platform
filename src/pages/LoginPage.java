package pages;

import helpers.ActionFactory;
import helpers.PageEnum;

public final class LoginPage extends Page {
    public LoginPage() {
        super(PageEnum.LOGIN_PAGE);
    }

    @Override
    public void setNextPages() {

    }

    @Override
    public void setActions() {
        this.getActions().add(ActionFactory.LOGIN);
    }
}
