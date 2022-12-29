package actions;

import database.User;
import fileio.ActionInput;
import server.Navigator;

public final class OnPageBuyTokens extends ActionStrategy {
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

        User currentUser = navigator.getCurrentPage().getCurrentUser();

        /**
         * Convert the given String balance to a working int
         */
        int currentBalance = Integer.parseInt(currentUser.getCredentials().getBalance());
        int currentTokens = currentUser.getTokensCount();
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

        currentUser.getCredentials().setBalance(Integer.toString(currentBalance));
        currentUser.setTokensCount(currentTokens);
    }
}
