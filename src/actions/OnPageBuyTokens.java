package actions;

import fileio.ActionInput;
import server.Navigator;

public class OnPageBuyTokens extends Action {
    private String page;
    private String feature;
    private String count;

    public OnPageBuyTokens(ActionInput action) {
        super(action.getType());
        page = action.getPage();
        feature = action.getFeature();
        count = action.getCount();
    }

    @Override
    public void doAction(Navigator navigator) {
        if (!navigator.getCurrentPage().checkAction(feature)) {
            setOutput("Error", new Navigator());
            return;
        }

        int currentBalance = Integer.parseInt(navigator.getCurrentUser().getCredentials().getBalance());
        int currentTokens = navigator.getCurrentUser().getTokensCount();
        int currentCount = Integer.parseInt(count);

        if (currentBalance < currentCount) {
            setOutput("Error", new Navigator());
            return;
        }

        currentBalance -= currentCount;
        currentTokens += currentCount;

        navigator.getCurrentUser().getCredentials().setBalance(Integer.toString(currentBalance));
        navigator.getCurrentUser().setTokensCount(currentTokens);
        setOutput(null, navigator);
    }
}
