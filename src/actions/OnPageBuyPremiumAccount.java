package actions;

import fileio.ActionInput;
import helpers.MagicNumbers;
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
        if (!navigator.getCurrentPage().checkAction(feature)) {
            setOutput("Error", new Navigator());
            return;
        }

        if (navigator.getCurrentUser().getCredentials().getAccountType().equals("premium")) {
            setOutput("Error", new Navigator());
            return;
        }

        int currentTokens = navigator.getCurrentUser().getTokensCount();

        if (currentTokens < MagicNumbers.TOKENS_FOR_PREMIUM) {
            setOutput("Error", new Navigator());
            return;
        }

        currentTokens -= MagicNumbers.TOKENS_FOR_PREMIUM;

        navigator.getCurrentUser().setTokensCount(currentTokens);
        navigator.getCurrentUser().getCredentials().setAccountType("premium");
    }
}
