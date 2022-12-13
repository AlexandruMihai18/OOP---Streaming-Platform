package actions;

import fileio.ActionInput;
import server.Navigator;

public final class OnPageBuyTokens extends Action {
    private String page;
    private String feature;
    private String count;

    public OnPageBuyTokens(final ActionInput action) {
        super(action.getType());
        page = action.getPage();
        feature = action.getFeature();
        count = action.getCount();
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
         * Convert the given String balance to a working int
         */
        int currentBalance = Integer
                .parseInt(navigator.getCurrentUser().getCredentials().getBalance());
        int currentTokens = navigator.getCurrentUser().getTokensCount();
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

        navigator.getCurrentUser().getCredentials().setBalance(Integer.toString(currentBalance));
        navigator.getCurrentUser().setTokensCount(currentTokens);
    }
}
