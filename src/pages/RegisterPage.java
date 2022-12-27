package pages;

import helpers.ActionFactory;
import helpers.PageEnum;

public final class RegisterPage extends Page {
    public RegisterPage() {
        super(PageEnum.REGISTER_PAGE);
    }

    @Override
    public void setNextPages() {

    }

    @Override
    public void setActions() {
        this.getActions().add(ActionFactory.REGISTER);
    }
}
