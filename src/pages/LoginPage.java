package pages;

import helpers.ActionsEnum;

public class LoginPage extends Page {
    public LoginPage() {
        super();
    }

    @Override
    public void setNextPages() {

    }

    @Override
    public void setActions() {
        this.getActions().add(ActionsEnum.LOGIN);
    }
}
