package op.strategies;

import datastore.DataStore;

public class StorePrice2 implements StorePrice {
    @Override
    public void storePrice(DataStore d) {
        int tempP = (Integer) d.getTemp_p();    // ✅ Correct cast from Object
        d.setPrice(tempP);
        System.out.println("[StorePrice2]: Price set to " + d.getPrice());
    }
}
