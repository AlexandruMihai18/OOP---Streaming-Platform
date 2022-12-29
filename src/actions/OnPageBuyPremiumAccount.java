package actions;

import database.User;
import fileio.ActionInput;
import helpers.Constants;
import server.Navigator;

public final class OnPageBuyPremiumAccount extends ActionStrategy {
    private String feature;

    public OnPageBuyPremiumAccount(final ActionInput action) {
        super(action.getType());
        feature = action.getFeature();
    }

    @Override
    public void actionStrategy(final Navigator navigator) {
        /**
         * Check the features for this page
         */
        if (!navigator.getCurrentPage().checkAction(feature)) {
            setError();
            return;
        }

        User currentUser = navigator.getCurrentPage().getCurrentUser();

        /**
         * Check if the account is already premium
         */
        if (currentUser.getCredentials().getAccountType().equals(Constants.PREMIUM)) {
            setError();
            return;
        }

        int currentTokens = currentUser.getTokensCount();

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

        currentUser.setTokensCount(currentTokens);
        currentUser.getCredentials().setAccountType(Constants.PREMIUM);
    }
}
