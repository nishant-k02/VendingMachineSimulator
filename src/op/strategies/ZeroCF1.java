package op.strategies;

import datastore.DataStore;
import datastore.DS1;

public class ZeroCF1 implements ZeroCF {
    @Override
    public void zeroCF(DataStore d) {
        if (d instanceof DS1) {
            d.setCF(0f);  // float 0
            System.out.println("[ZeroCF1]: CF reset to 0.0");
        }
    }
}
