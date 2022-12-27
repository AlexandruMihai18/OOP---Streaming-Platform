package actions;

import fileio.ActionInput;
import helpers.Constants;
import server.Navigator;

public final class OnPageBuyPremiumAccount extends Action {
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

        /**
         * Check if the account is already premium
         */
        if (navigator.getCurrentPage().getCurrentUser().getCredentials().getAccountType()
                .equals(Constants.PREMIUM)) {
            setError();
            return;
        }

        int currentTokens = navigator.getCurrentPage().getCurrentUser().getTokensCount();

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

        navigator.getCurrentPage().getCurrentUser().setTokensCount(currentTokens);
        navigator.getCurrentPage().getCurrentUser().getCredentials().setAccountType("premium");
    }
}
