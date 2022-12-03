package helpers;

import actions.*;
import fileio.ActionInput;

public final class ActionBuilder {
    private ActionBuilder() {

    }

    public static Action buildAction(final ActionInput action) {
       switch (action.getType()) {
            case ActionsEnum.CHANGE_PAGE_TYPE -> {
                if (action.getPage().equals(PageEnum.SEE_DETAILS_PAGE)) {
                    return new ChangePageMovieAction(action);
                }
                return new ChangePageAction(action);
            }
            case ActionsEnum.ON_PAGE_TYPE -> {
                switch (action.getFeature()) {
                    case ActionsEnum.LOGIN -> {
                        return new OnPageLogin(action);
                    }
                    case ActionsEnum.REGISTER -> {
                        return new OnPageRegister(action);
                    }
                    case ActionsEnum.SEARCH -> {
                        return new OnPageSearch(action);
                    }
                }
            }
       }
       return null;
    }
}
