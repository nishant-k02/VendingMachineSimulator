package op.strategies;

import datastore.DataStore;

// [STRATEGY PATTERN] Concrete Strategy for VM1

public class StorePrice1 implements StorePrice {
    @Override
    public void storePrice(DataStore d) {
        d.setPrice(d.getTemp_p());
        System.out.println("[StorePrice1]: Price set to " + d.getPrice());
    }
}
