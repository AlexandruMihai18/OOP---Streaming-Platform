package helpers;

import actions.*;
import fileio.ActionInput;

/**
 * Factory Methode Pattern -- returns a specific type of action
 */
public final class ActionFactory {
    private ActionFactory() {

    }

    public static final String CHANGE_PAGE_TYPE = "change page";
    public static final String ON_PAGE_TYPE = "on page";
    public static final String LOGIN = "login";
    public static final String REGISTER = "register";
    public static final String SEARCH = "search";
    public static final String FILTERS = "filter";
    public static final String PURCHASE = "purchase";
    public static final String WATCH = "watch";
    public static final String LIKE = "like";
    public static final String RATE_THE_MOVIE = "rate";
    public static final String BUY_PREMIUM_ACCOUNT = "buy premium account";
    public static final String BUY_TOKENS = "buy tokens";
    public static final String SUBSCRIBE = "subscribe";
    public static final String DATABASE = "database";
    public static final String ADD_DATABASE = "add";
    public static final String DELETE_DATABASE = "delete";
    public static final String BACK = "back";

    /**
     * Assess the type of the action and return a specific one
     * @param action given input action
     * @return specific type of action
     */
    public static ActionStrategy createAction(final ActionInput action) {
       switch (action.getType()) {
            case CHANGE_PAGE_TYPE -> {
                if (action.getPage().equals(PageEnum.SEE_DETAILS_PAGE)) {
                    return new ChangePageMovieAction(action);
                } else {
                    return new ChangePageAction(action);
                }
            }
            case ON_PAGE_TYPE -> {
                switch (action.getFeature()) {
                    case LOGIN -> {
                        return new OnPageLogin(action);
                    }
                    case REGISTER -> {
                        return new OnPageRegister(action);
                    }
                    case SEARCH -> {
                        return new OnPageSearch(action);
                    }
                    case FILTERS -> {
                        return new OnPageFilters(action);
                    }
                    case BUY_PREMIUM_ACCOUNT -> {
                        return new OnPageBuyPremiumAccount(action);
                    }
                    case BUY_TOKENS -> {
                        return new OnPageBuyTokens(action);
                    }
                    case PURCHASE -> {
                        return new OnPagePurchase(action);
                    }
                    case WATCH -> {
                        return new OnPageWatch(action);
                    }
                    case LIKE -> {
                        return new OnPageLike(action);
                    }
                    case RATE_THE_MOVIE -> {
                        return new OnPageRateMovie(action);
                    }
                    case SUBSCRIBE -> {
                        return new OnPageSubscribe(action);
                    }
                    default -> {
                        return null;
                    }
                }
            }
           case DATABASE -> {
                switch (action.getFeature()) {
                    case ADD_DATABASE -> {
                        return new DatabaseAdd(action);
                    }
                    case DELETE_DATABASE -> {
                        return new DatabaseDelete(action);
                    }
                    default -> {
                        return null;
                    }
                }
           }
           case BACK -> {
                return new Back(action);
           }
            default -> {
                return null;
            }
       }
    }
}
