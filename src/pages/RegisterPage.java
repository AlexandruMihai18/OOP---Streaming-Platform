package pages;

import helpers.ActionFactory;

public final class RegisterPage extends Page {
    public RegisterPage() {
        super();
    }

    @Override
    public void setNextPages() {

    }

    @Override
    public void setActions() {
        this.getActions().add(ActionFactory.REGISTER);
    }
}
