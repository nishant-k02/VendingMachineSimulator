package op.strategies;

import datastore.DataStore;
import datastore.DS2;

public class ZeroCF2 implements ZeroCF {
    @Override
    public void zeroCF(DataStore d) {
        if (d instanceof DS2) {
            d.setCF(0);
            System.out.println("[ZeroCF2] CF reset to 0");
        }
    }
}
