package op.strategies;

import datastore.DataStore;

public class ReturnCoins2 implements ReturnCoins {
    @Override
    public void returnCoins(DataStore d) {
        System.out.println("Returning coins for VM2...");
    }
}
