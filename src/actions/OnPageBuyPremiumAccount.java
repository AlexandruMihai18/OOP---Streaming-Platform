package actions;

import fileio.ActionInput;
import helpers.Constants;
import server.Navigator;

public final class OnPageBuyPremiumAccount extends Action {
    private String page;
    private String feature;

    public OnPageBuyPremiumAccount(final ActionInput action) {
        super(action.getType());
        page = action.getPage();
        feature = action.getFeature();
    }

    @Override
    public void doAction(final Navigator navigator) {
        /**
         * Check the features for this page
         */
        if (!navigator.getCurrentPage().checkAction(feature)) {
            setError();
            return;
        }

        /**
         * Check if the account is already premium
         */
        if (navigator.getCurrentUser().getCredentials().getAccountType()
                .equals(Constants.PREMIUM)) {
            setError();
            return;
        }

        int currentTokens = navigator.getCurrentUser().getTokensCount();

        /**
         * Check if the user has enough tokens to buy premium account
         */
        if (currentTokens < Constants.TOKENS_FOR_PREMIUM) {
            setError();
            return;
        }

        /**
         * Buy premium account using tokens
         */
        currentTokens -= Constants.TOKENS_FOR_PREMIUM;

        navigator.getCurrentUser().setTokensCount(currentTokens);
        navigator.getCurrentUser().getCredentials().setAccountType("premium");
    }
}
