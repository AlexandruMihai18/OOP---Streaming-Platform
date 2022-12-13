package pages;

import helpers.ActionFactory;

public final class LoginPage extends Page {
    public LoginPage() {
        super();
    }

    @Override
    public void setNextPages() {

    }

    @Override
    public void setActions() {
        this.getActions().add(ActionFactory.LOGIN);
    }
}
