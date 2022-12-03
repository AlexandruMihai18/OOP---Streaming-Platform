package pages;

import helpers.ActionsEnum;

public class RegisterPage extends Page {
    public RegisterPage() {
        super();
    }

    @Override
    public void setNextPages() {

    }

    @Override
    public void setActions() {
        this.getActions().add(ActionsEnum.REGISTER);
    }
}
