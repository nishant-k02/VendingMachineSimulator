package op.strategies;

import datastore.DataStore;

public class StorePrice2 implements StorePrice {
    @Override
    public void storePrice(DataStore d) {
        d.setPrice(d.getTemp_p());
    }
}
