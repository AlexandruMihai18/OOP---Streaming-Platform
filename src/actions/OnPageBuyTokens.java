package actions;

import fileio.ActionInput;
import server.Navigator;

public final class OnPageBuyTokens extends Action {
    private String feature;
    private String count;

    public OnPageBuyTokens(final ActionInput action) {
        super(action.getType());
        feature = action.getFeature();
        count = action.getCount();
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
         * Convert the given String balance to a working int
         */
        int currentBalance = Integer
                .parseInt(navigator.getCurrentPage().getCurrentUser().getCredentials().getBalance());
        int currentTokens = navigator.getCurrentPage().getCurrentUser().getTokensCount();
        int currentCount = Integer.parseInt(count);

        /**
         * The user does not have enough balance to buy tokens
         */
        if (currentBalance < currentCount) {
            setError();
            return;
        }

        /**
         * Buy tokens using balance
         */
        currentBalance -= currentCount;
        currentTokens += currentCount;

        navigator.getCurrentPage().getCurrentUser().getCredentials().setBalance(Integer.toString(currentBalance));
        navigator.getCurrentPage().getCurrentUser().setTokensCount(currentTokens);
    }
}
