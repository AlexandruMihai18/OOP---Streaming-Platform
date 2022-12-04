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
                } else {
                    return new ChangePageAction(action);
                }
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
                    case ActionsEnum.FILTERS -> {
                        return new OnPageFilters(action);
                    }
                    case ActionsEnum.BUY_PREMIUM_ACCOUNT -> {
                        return new OnPageBuyPremiumAccount(action);
                    }
                    case ActionsEnum.BUY_TOKENS -> {
                        return new OnPageBuyTokens(action);
                    }
                    case ActionsEnum.PURCHASE -> {
                        return new OnPagePurchase(action);
                    }
                    case ActionsEnum.WATCH -> {
                        return new OnPageWatch(action);
                    }
                    case ActionsEnum.LIKE -> {
                        return new OnPageLike(action);
                    }
                    case ActionsEnum.RATE_THE_MOVIE -> {
                        return new OnPageRateMovie(action);
                    }
                }
            }
       }
       return null;
    }
}
